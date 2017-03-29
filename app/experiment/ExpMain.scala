package experiment

import java.awt.event.{WindowEvent, WindowListener}
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame

import com.github.sarxos.webcam.{Webcam, WebcamPanel, WebcamResolution}
import org.jcodec.api.SequenceEncoder
import org.jcodec.common.model.ColorSpace

/**
  * Author: Rinat Tainov 
  * Date: 28/03/2017
  */
object ExpMain extends App {
  val webcam = Webcam.getDefault()
  webcam.setViewSize(WebcamResolution.VGA.getSize)

  val panel = new WebcamPanel(webcam)
  panel.setFPSDisplayed(true)
  panel.setDisplayDebugInfo(true)
  panel.setImageSizeDisplayed(true)
  panel.setMirrored(true)

  val window = new JFrame("Test webcam panel")
  window.addWindowListener(new WindowListener() {

    @Override
    def windowClosed(e: WindowEvent) {
      panel.stop()
      webcam.close()
    }

    @Override
    def windowOpened(e: WindowEvent) {}

    @Override
    def windowIconified(e: WindowEvent) {}

    @Override
    def windowDeiconified(e: WindowEvent) {}

    @Override
    def windowDeactivated(e: WindowEvent) {}

    @Override
    def windowClosing(e: WindowEvent) {}

    @Override
    def windowActivated(e: WindowEvent) {}
  })
  window.add(panel)
  window.setResizable(true)
  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  window.pack()
  window.setVisible(true)

  import java.awt.image.BufferedImage

  import org.jcodec.common.model.Picture

  saveClip

  //  saveImage

  def saveImage = {
    ImageIO.write(webcam.getImage, "jpg", new File(s"/Users/taintech/Downloads/testimage${System.currentTimeMillis()}.jpg"))
  }

  private def saveClip = {
    val enc = new SequenceEncoder(new File("/Users/taintech/Downloads/testjcode.mp4"))
    for {
      i <- 1 to 10
      src = webcam.getImage
    } {
      saveImage
      enc.encodeNativeFrame(Picture.create(src.getWidth, src.getHeight, ColorSpace.RGB))
      Thread.sleep(100)
    }
    enc.finish()
  }


  def fromBufferedImage(src: BufferedImage, dst: Picture): Unit = {
    val dstData = dst.getPlaneData(0)
    var off = 0
    var i = 0
    while ( {
      i < src.getHeight
    }) {
      var j = 0
      while ( {
        j < src.getWidth
      }) {
        val rgb1 = src.getRGB(j, i)
        dstData({
          off += 1;
          off - 1
        }) = (rgb1 >> 16) & 0xff
        dstData({
          off += 1;
          off - 1
        }) = (rgb1 >> 8) & 0xff
        dstData({
          off += 1;
          off - 1
        }) = rgb1 & 0xff

        {
          j += 1;
          j - 1
        }
      }

      {
        i += 1;
        i - 1
      }
    }
  }
}


