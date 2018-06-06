package org.tron.program;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.tron.constant.Contant.Identity;
import org.tron.constant.Contant.NetType;
import org.tron.crypto.ECKey;
import org.tron.utils.ByteArray;
import org.tron.utils.Utils;

public class GenerateAccountTool {
  @Parameter(names={"--number", "-n"})
  int number = 1;

  @Parameter(names={"--netType"}, description = "netType: testnet/mainnet")
  String netType = "";

  public static byte addressPrefix;

  public static void main(String[] args) {
    GenerateAccountTool tool = new GenerateAccountTool();
    JCommander.newBuilder()
        .addObject(tool)
        .build()
        .parse(args);

    tool.exe();
  }

  private void exe() {
    if (netType.equals(NetType.MAINNET)) {
      addressPrefix = Identity.ADDRESS_PREFIX_MAINNET;
    } else if (netType.equals(NetType.TESTNET)) {
      addressPrefix = Identity.ADDRESS_PREFIX_TESTNET;
    } else {
      System.out.println("\u001B[31mrun with: --netTpey [testnet/mainnet]\u001B[0m");
      return;
    }

    for (int i = 0; i < number; i++) {
      ECKey ecKey = new ECKey(Utils.getRandom());
      System.out.printf("Order: \u001B[34m%d\u001B[0m\n", (i + 1));
      System.out.printf("Private Key: \u001B[34m%s\u001B[0m\n", ByteArray.toHexString(ecKey.getPrivKeyBytes()));
      System.out.printf("Public Key: \u001B[34m%s\u001B[0m\n", ByteArray.toHexString(ecKey.getPubKey()));
      System.out.printf("Address(Base58): \u001B[34m%s\u001B[0m\n", Utils.encode58Check(ecKey.getAddress()));
      System.out.printf("Address(Hex): \u001B[34m%s\u001B[0m\n", ByteArray.toHexString(ecKey.getAddress()));
      System.out.println();
    }
  }
}
