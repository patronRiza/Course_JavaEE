package jrp.figures.template;

import jrp.figures.Figure;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TemplateJSON {

    private Map<String, Figure> figures;

    @Override
    public String toString() {
        return "\nTemplateJSON{" +
                "   figures=\n" + figures +
                '}';
    }
}
