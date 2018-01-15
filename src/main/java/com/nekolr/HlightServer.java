package com.nekolr;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.util.ServerRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author nekolr
 * @date 2018.01.15
 */
public class HlightServer extends NanoHTTPD {

    private static final Logger LOG = Logger.getLogger(NanoHTTPD.class.getName());

    public static void main(String[] args) {
        ServerRunner.run(HlightServer.class);
    }

    public HlightServer() {
        super(8080);
    }

    @Override
    public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        String uri = session.getUri();
        HlightServer.LOG.info(session.getRemoteIpAddress() + " " + method + " '" + uri + "' ");
        // POST需要parseBody
        try {
            session.parseBody(new HashMap<String, String>(2));
        } catch (IOException e) {
            return newFixedLengthResponse(NanoHTTPD.Response.Status.INTERNAL_ERROR, "text/plain", "SERVER INTERNAL ERROR: IOException: " + e.getMessage());
        } catch (NanoHTTPD.ResponseException e) {
            return newFixedLengthResponse(e.getStatus(), "text/plain", e.getMessage());
        }
        return newFixedLengthResponse(this.renderHtml(session.getParms()));
    }

    private String renderHtml(Map<String, String> params) {
        StringBuilder sb = new StringBuilder("");
        String lan = params.get("lan");
        String code = params.get("code");
        sb.append("<!DOCTYPE html>");
        sb.append("<html lang='en'>");
        sb.append("<head>");
        sb.append("<meta charset='UTF-8'>");
        sb.append("<title>Highlight Your Code</title>");
        sb.append("<link rel='stylesheet' type='text/css' href='https://nekolr.com/SyntaxHighlighter/styles/SyntaxHighlighter.css'>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<pre class='" + lan + "' name='code' id='code'>");
        sb.append("" + code + "");
        sb.append("</pre>");
        sb.append("</body>");
        sb.append("<script src='https://nekolr.com/SyntaxHighlighter/scripts/shCore.js'></script>");
        sb.append("<script src='https://nekolr.com/SyntaxHighlighter/scripts/" + lan + ".js'></script>");
        sb.append("<script type='text/javascript'>");
        sb.append("dp.SyntaxHighlighter.HighlightAll('code', true, false);");
        sb.append("document.body.removeChild(document.getElementById('code'));");
        sb.append("</script>");
        sb.append("</html>");
        return sb.toString();
    }
}
