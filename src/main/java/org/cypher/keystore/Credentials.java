package org.cypher.keystore;


import org.cypher.common.crypto.SignInterface;

public interface Credentials {
  SignInterface getPair();

  String getAddress();
}
