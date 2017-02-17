package bpmnviz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import com.mxgraph.util.mxCellRenderer;
import org.yaoqiang.bpmn.editor.swing.BPMNGraphComponent;
import org.yaoqiang.bpmn.editor.view.BPMNGraph;
import org.yaoqiang.bpmn.model.BPMNModelParsingErrors;
import org.yaoqiang.graph.io.bpmn.BPMNCodec;
import org.yaoqiang.graph.model.GraphModel;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: BPMNviz <FOLDER>");
            return;
        }

        Path folder = Paths.get(args[0]);

        if (!Files.isDirectory(folder)) {
            System.out.println(folder + " is no folder");
            return;
        }

        Files.walk(folder).filter((p) -> p.toString().endsWith(".bpmn") || p.toString().endsWith(".bpmn2")).forEach(Main::createImage);

    }

    public static void createImage(Path path) {
        try {
            Path bpmnImagePath = path.getParent().resolve(path.getFileName().toString() + ".png");
            System.out.println("creating bpmn image for path " + path + " at " + bpmnImagePath);

            String bpmnxmlfile = path.toString();

            BPMNGraph graph = new BPMNGraph(new GraphModel());
            BPMNGraphComponent graphComponent = new BPMNGraphComponent(graph);

            BPMNCodec codec = new BPMNCodec(graph);
            List<BPMNModelParsingErrors.ErrorMessage> errorMessages = codec.decode(bpmnxmlfile);
            if (errorMessages.size() > 0) {
                return;
            }

            BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, null, graphComponent.isAntiAlias(), null, true, graphComponent.getCanvas());

            File outputfile = bpmnImagePath.toFile();
            try {
                ImageIO.write(image, "png", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Could not create image for " + path + " because of " + e.getMessage());
        }
    }

}
