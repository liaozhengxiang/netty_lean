package com.liao.netty.nio;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

public class SelectorTest {


    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        while (true) {

            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isConnectable()) {
                    SelectableChannel channel = selectionKey.channel();

                }

                selectionKeys.clear();
            }

        }


    }

}
