package ru.library.utils;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableRenderer {

    public String tableRender(List<String> titles, List<List<String>> test) {
        AsciiTable at = settingTable(titles);
        test.forEach(x -> {
            at.addRow(x);
            at.setTextAlignment(TextAlignment.CENTER);
            at.addRule();
        });
        return at.render();
    }

    public AsciiTable settingTable(List<String> titles) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(titles);
        at.setPaddingTopChar('-');
        at.setPaddingBottomChar('_');
        at.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        at.setPadding(1);
        return at;
    }
}
