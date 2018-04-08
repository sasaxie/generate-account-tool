package org.tron.program;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.tron.crypto.ECKey;
import org.tron.utils.ByteArray;
import org.tron.utils.Utils;

public class GenerateAccountTool {
  @Parameter(names={"--number", "-n"})
  int number = 1;

  public static void main(String[] args) {
    GenerateAccountTool tool = new GenerateAccountTool();
    JCommander.newBuilder()
        .addObject(tool)
        .build()
        .parse(args);

    tool.exe();
  }

  private void exe() {
    for (int i = 0; i < number; i++) {
      ECKey ecKey = new ECKey(Utils.getRandom());
      System.out.println("Order: " + (i + 1));
      System.out.println("Private Key: " + ByteArray.toHexString(ecKey.getPrivKeyBytes()));
      System.out.println("Public Key: " + ByteArray.toHexString(ecKey.getPubKey()));
      System.out.println("Address: " + ByteArray.toHexString(ecKey.getAddress()));
      System.out.println();
    }
  }
}
