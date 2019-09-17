package app.admin

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class UtilityService {

    def uriToImage(String uri) {
        try {
            URL url = new URL(uri)

            // read image direct from url
            BufferedImage image = ImageIO.read(url)

            // write image to outputstream
            ByteArrayOutputStream baos = new ByteArrayOutputStream()
            ImageIO.write(image, "png", baos)
            baos.flush()

            // get bytes
            byte[] imageBytes = baos.toByteArray()
            return imageBytes
        } catch (Error e) {
            log.error(e)
            return null
        }
    }
}
