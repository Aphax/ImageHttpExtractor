package com.aphax.ImageHttpExtractor;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) throws IOException {
    final URL url = new URL(args[0]);
    final String urlBody = IOUtils.toString(url);
    final Pattern pattern = Pattern.compile("(i\\.imgur\\.com/([A-Za-z0-9]+\\.jpg))");
    final Matcher matcher = pattern.matcher(urlBody);
    int i = 1;
    while (matcher.find()) {
      System.out.println("Downloading image " + i + " : " + matcher.group(1));
      downloadFile(matcher.group(1), "C:\\Users\\Aphax\\Pictures\\Wallpapers\\Star Wars\\SW_" + matcher.group(2));
      i++;
    }
  }

  private static String readFile(String path, Charset encoding)
          throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }

  public static void downloadFile(String url, String filepath) throws IOException {
    URL website = new URL("http://" + url);
    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
    FileOutputStream fos = new FileOutputStream(filepath);
    final long l = fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
  }
}
