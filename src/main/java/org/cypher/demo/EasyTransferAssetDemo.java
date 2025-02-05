package org.cypher.demo;

import org.cypher.api.GrpcAPI.EasyTransferResponse;
import org.cypher.common.crypto.ECKey;
import org.cypher.common.crypto.Sha256Sm3Hash;
import org.cypher.common.utils.ByteArray;
import org.cypher.common.utils.Utils;
import org.cypher.protos.Protocol.Transaction;
import org.cypher.walletserver.WalletApi;

import java.util.Arrays;

public class EasyTransferAssetDemo {

  private static byte[] getAddressByPassphrase(String passPhrase) {
    byte[] privateKey = Sha256Sm3Hash.hash(passPhrase.getBytes());
    ECKey ecKey = ECKey.fromPrivate(privateKey);
    byte[] address = ecKey.getAddress();
    return address;
  }

  public static void main(String[] args) {
    String passPhrase = "test pass phrase";
    byte[] address = WalletApi.createAdresss(passPhrase.getBytes());
    String tokenId = "1000001";
    if (!Arrays.equals(address, getAddressByPassphrase(passPhrase))) {
      System.out.println("The address is diffrent !!");
    }
    System.out.println("address === " + WalletApi.encode58Check(address));

    EasyTransferResponse response = WalletApi
        .easyTransferAsset(
            passPhrase.getBytes(), getAddressByPassphrase("test pass phrase 2"),
            tokenId, 10000L);
    if (response.getResult().getResult() == true) {
      Transaction transaction = response.getTransaction();
      System.out.println("Easy transfer successful!!!");
      System.out.println(
          "Receive txid = " + ByteArray.toHexString(response.getTxid().toByteArray()));
      System.out.println(Utils.printTransaction(transaction));
    } else {
      System.out.println("Easy transfer failed!!!");
      System.out.println("Code = " + response.getResult().getCode());
      System.out.println("Message = " + response.getResult().getMessage().toStringUtf8());
    }
  }
}
