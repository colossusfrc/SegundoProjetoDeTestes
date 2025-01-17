package frc.robot.SubsystemResources;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera extends Subsystem{
    private final UsbCamera camera;
    public final CvSink cvSink;
    public final CvSource cvSource;
    public final CascadeClassifier cascadeClassifier;
    public Camera(){ 
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        this.camera = CameraServer.startAutomaticCapture();
        this.camera.setResolution(640, 480);

        cvSink = CameraServer.getVideo();
        cvSource = CameraServer.putVideo("Face Detection", 640, 480);
        cascadeClassifier = new CascadeClassifier("C:\\Users\\COLOSSUS\\Documents\\SegundoProjetoDeTestes\\src\\main\\res\\haarcascade_frontalface_default.xml");
        if (cascadeClassifier.empty()) {
            System.err.println("Erro ao carregar o classificador de rostos");
            return;
        }
    }
    public void setResolution(int n1, int n2){
        camera.setResolution(n1, n2);
    }
    public void detectAndDisplay(Mat frame) {
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
        MatOfRect faces = new MatOfRect();
        SmartDashboard.putNumber("Numebr Of Faces: ", faces.toArray().length);
        
        cascadeClassifier.detectMultiScale(grayFrame, faces);

        for (Rect face : faces.toArray()) {
            Imgproc.rectangle(frame, new Point(face.x, face.y),
                    new Point(face.x + face.width, face.y + face.height),
                    new Scalar(0, 255, 0));
        }
    }
}
