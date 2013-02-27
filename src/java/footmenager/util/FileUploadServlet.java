/*
 Classes used for image processing.
FileUploadServlet uploads an image and stores its
content in the database. ImageServlet retrieves the
image content from the database and displays it
(JavaServer Faces technology does not provide this
functionality, so a servlet is needed).
 */


package footmenager.util;


import entgen.TeamGen;
import footmenager.ejb.TeamBean;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet(urlPatterns = "/fileUpload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(
                FileUploadServlet.class.getCanonicalName());
    private static final List<String> EXTENSIONS_ALLOWED = new ArrayList<String>();

    static {
        // images only
        EXTENSIONS_ALLOWED.add(".jpg");
        EXTENSIONS_ALLOWED.add(".bmp");
        EXTENSIONS_ALLOWED.add(".png");
        EXTENSIONS_ALLOWED.add(".gif");
    }

    TeamGen team;
    @EJB
    TeamBean teamBean;

    public FileUploadServlet() {
        super();
    }

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        InputStream is = null;

        try {
            if ((request.getParameter("productID") != null)
                    //getParts bierze to co zaznczylem w wyskakujacym oknie
                    && (request.getParts()//Gets all the Part components of this request, provided that it is of type multipart/form-data.
                                   .size() > 0)) {
                for (Part part : request.getParts()) {
                    logger.info(part.getName());

                    //czytam z pliku
                    is = request.getPart(part.getName())
                                .getInputStream();

                    int i = is.available();/*Returns an estimate of the number of bytes that can be read (or skipped over)
                     * from this input stream without blocking by the next invocation of a method for this input stream*/
                    byte[] b = new byte[i];
                    is.read(b);//czyta z is do byte[] b

                    logger.log(Level.INFO, "Length : {0}", b.length);

                    String fileName = getFileName(part);
                    logger.log(Level.INFO, "File name : {0}", fileName);

                    Long id = Long.parseLong(
                                request.getParameter("productID"));

                    team = teamBean.find(id);

                    // generate *unique* filename 
                    final String extension = fileName.substring(
                                fileName.length() - 4);//substirng = .jpg

                    if (!EXTENSIONS_ALLOWED.contains(extension)) {
                        logger.severe(
                                "User tried to upload file that's not an image. Upload canceled.");
                        response.sendRedirect(
                                "admin/product/List.xhtml?errMsg=Error trying to upload file");

                        return;
                    }

                    //final String newFilename = MD5Util.generateMD5(fileName) + extension;    //id + extension;
                    team.setImgSrc(b);
                    team.setImg(fileName);
                    teamBean.edit(team);

                    response.sendRedirect("admin/team/Confirm.xhtml");
                }
            } else {
                // no img case
                response.sendRedirect("admin/team/Confirm.xhtml");
            }

            // very generic error threatment - just sample
        } catch (NumberFormatException nfe) {
            logger.log(
                    Level.SEVERE,
                    "Error to process product information - ",
                    nfe.getMessage());
        } catch (IOException ioe) {
            logger.log(
                    Level.SEVERE,
                    "Error during file upload - ",
                    ioe.getMessage());
        } catch (StringIndexOutOfBoundsException soe) {
            logger.log(
                    Level.SEVERE,
                    "Proceeding without an image",
                    soe.getMessage());

            response.sendRedirect("admin/product/Confirm.xhtml");
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private String getFileName(Part part) {
        String partHeader = part.getHeader("content-disposition");//Returns the value of the specified mime header as a String
        //zwroci Part Header = form-data; name="uploadFile"; filename="real_LW.jpg"
        logger.log(Level.INFO, "Part Header = {0}", partHeader);

        for (String cd : part.getHeader("content-disposition")
                             .split(";")) {
            if (cd.trim()
                      .startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1)
                         .trim()
                         .replace("\"", "");
                //po tej operacji return real_LW.jpg
            }
        }

        return null;
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
