package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.sun.xml.bind.api.impl.NameConverter.Standard;

public class Uri2PackageNameConverter extends Standard {

    public Uri2PackageNameConverter() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toPackageName(String nsUri) {
        // remove scheme and :, if present
        // spec only requires us to remove 'http' and 'urn'...
        int idx = nsUri.indexOf(':');
        String scheme = "";
        if (idx >= 0) {
            scheme = nsUri.substring(0, idx);
            if (scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("urn"))
                nsUri = nsUri.substring(idx + 1);
        }

        // issue 709; s/(.*)#(.*)/\1/
        idx = nsUri.indexOf("#");
        if (idx >= 0)
            nsUri = nsUri.substring(0, idx);

        // tokenize string
        ArrayList<String> tokens = tokenize(nsUri, "/: ");
        if (tokens.size() == 0) {
            return null;
        }

        // remove trailing file type, if necessary
        if (tokens.size() > 1) {
            // for uri's like "www.foo.com" and "foo.com", there is no trailing
            // file, so there's no need to look at the last '.' and substring
            // otherwise, we loose the "com" (which would be wrong)
            String lastToken = tokens.get(tokens.size() - 1);
            idx = lastToken.lastIndexOf('.');
            if (idx > 0) {
                lastToken = lastToken.substring(0, idx);
                tokens.set(tokens.size() - 1, lastToken);
            }
        }

        // tokenize domain name and reverse. Also remove :port if it exists
        String domain = tokens.get(0);
        idx = domain.indexOf(':');
        if (idx >= 0)
            domain = domain.substring(0, idx);
        ArrayList<String> r = tokenize(domain, scheme.equals("urn") ? ".-" : ".");
        if (r.get(r.size() - 1).equalsIgnoreCase("www")) {
            // remove leading www
            r.remove(r.size() - 1);
        }

        // replace the domain name with tokenized items
        tokens.addAll(1, r);
        tokens.remove(0);

        // iterate through the tokens and apply xml->java name algorithm
        for (int i = 0; i < tokens.size(); i++) {

            // get the token and remove illegal chars
            String token = tokens.get(i);
            token = removeIllegalIdentifierChars(token);

            // this will check for reserved keywords
            if (!super.isJavaIdentifier(token.toLowerCase())) {
                token = '_' + token;
            }

            tokens.set(i, token.toLowerCase());
        }

        // concat all the pieces and return it
        return combine(tokens, '.');
    }

    private static ArrayList<String> tokenize(String str, String sep) {
        StringTokenizer tokens = new StringTokenizer(str, sep);
        ArrayList<String> r = new ArrayList<String>();

        while (tokens.hasMoreTokens())
            r.add(tokens.nextToken());

        return r;
    }

    private static String removeIllegalIdentifierChars(String token) {
        StringBuffer newToken = new StringBuffer();
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);

            if (i == 0 && !Character.isJavaIdentifierStart(c)) {
                // prefix an '_' if the first char is illegal
                newToken.append('_').append(c);
            } else if (!Character.isJavaIdentifierPart(c)) {
                // replace the char with an '_' if it is illegal
                newToken.append('_');
            } else {
                // add the legal char
                newToken.append(c);
            }
        }
        return newToken.toString();
    }

    private static String combine(List r, char sep) {
        StringBuilder buf = new StringBuilder(r.get(0).toString());

        for (int i = 1; i < r.size(); i++) {
            buf.append(sep);
            buf.append(r.get(i));
        }

        return buf.toString();
    }
}
