package by.alex.bsuir.instagram.tags;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PostImage extends SimpleTagSupport {
    private byte[] imageByte;

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (imageByte != null && imageByte.length > 0) {
            String base64Encoded = Base64.encodeBase64String(imageByte);
            out.print("data:image/png;base64," + base64Encoded);
        }
    }
}
