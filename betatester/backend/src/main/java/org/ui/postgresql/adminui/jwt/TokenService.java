package org.ui.postgresql.adminui.jwt;

public interface TokenService<V> {

    TokenObject decryptToken(String token);

    String createToken(V data);
}
