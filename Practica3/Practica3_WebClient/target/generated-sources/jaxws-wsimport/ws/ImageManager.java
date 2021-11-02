
package ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ImageManager", targetNamespace = "http://WS/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ImageManager {


    /**
     * 
     * @param user
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod(operationName = "CheckPassword")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "CheckPassword", targetNamespace = "http://WS/", className = "ws.CheckPassword")
    @ResponseWrapper(localName = "CheckPasswordResponse", targetNamespace = "http://WS/", className = "ws.CheckPasswordResponse")
    @Action(input = "http://WS/ImageManager/CheckPasswordRequest", output = "http://WS/ImageManager/CheckPasswordResponse")
    public Boolean checkPassword(
        @WebParam(name = "user", targetNamespace = "")
        User user);

    /**
     * 
     * @param id
     * @return
     *     returns java.lang.Integer
     * @throws IOException_Exception
     */
    @WebMethod(operationName = "DeleteImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "DeleteImage", targetNamespace = "http://WS/", className = "ws.DeleteImage")
    @ResponseWrapper(localName = "DeleteImageResponse", targetNamespace = "http://WS/", className = "ws.DeleteImageResponse")
    @Action(input = "http://WS/ImageManager/DeleteImageRequest", output = "http://WS/ImageManager/DeleteImageResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://WS/ImageManager/DeleteImage/Fault/IOException")
    })
    public Integer deleteImage(
        @WebParam(name = "id", targetNamespace = "")
        int id)
        throws IOException_Exception
    ;

    /**
     * 
     * @param image
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod(operationName = "ModifyImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ModifyImage", targetNamespace = "http://WS/", className = "ws.ModifyImage")
    @ResponseWrapper(localName = "ModifyImageResponse", targetNamespace = "http://WS/", className = "ws.ModifyImageResponse")
    @Action(input = "http://WS/ImageManager/ModifyImageRequest", output = "http://WS/ImageManager/ModifyImageResponse")
    public Integer modifyImage(
        @WebParam(name = "image", targetNamespace = "")
        Image image);

    /**
     * 
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod(operationName = "GetMaxId")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "GetMaxId", targetNamespace = "http://WS/", className = "ws.GetMaxId")
    @ResponseWrapper(localName = "GetMaxIdResponse", targetNamespace = "http://WS/", className = "ws.GetMaxIdResponse")
    @Action(input = "http://WS/ImageManager/GetMaxIdRequest", output = "http://WS/ImageManager/GetMaxIdResponse")
    public Integer getMaxId();

    /**
     * 
     * @param user
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod(operationName = "CheckUser")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "CheckUser", targetNamespace = "http://WS/", className = "ws.CheckUser")
    @ResponseWrapper(localName = "CheckUserResponse", targetNamespace = "http://WS/", className = "ws.CheckUserResponse")
    @Action(input = "http://WS/ImageManager/CheckUserRequest", output = "http://WS/ImageManager/CheckUserResponse")
    public Boolean checkUser(
        @WebParam(name = "user", targetNamespace = "")
        User user);

    /**
     * 
     * @param user
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod(operationName = "CreateUser")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "CreateUser", targetNamespace = "http://WS/", className = "ws.CreateUser")
    @ResponseWrapper(localName = "CreateUserResponse", targetNamespace = "http://WS/", className = "ws.CreateUserResponse")
    @Action(input = "http://WS/ImageManager/CreateUserRequest", output = "http://WS/ImageManager/CreateUserResponse")
    public Integer createUser(
        @WebParam(name = "user", targetNamespace = "")
        User user);

    /**
     * 
     * @return
     *     returns java.util.List<ws.Image>
     */
    @WebMethod(operationName = "ListImages")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ListImages", targetNamespace = "http://WS/", className = "ws.ListImages")
    @ResponseWrapper(localName = "ListImagesResponse", targetNamespace = "http://WS/", className = "ws.ListImagesResponse")
    @Action(input = "http://WS/ImageManager/ListImagesRequest", output = "http://WS/ImageManager/ListImagesResponse")
    public List<Image> listImages();

    /**
     * 
     * @param image
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod(operationName = "RegisterImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "RegisterImage", targetNamespace = "http://WS/", className = "ws.RegisterImage")
    @ResponseWrapper(localName = "RegisterImageResponse", targetNamespace = "http://WS/", className = "ws.RegisterImageResponse")
    @Action(input = "http://WS/ImageManager/RegisterImageRequest", output = "http://WS/ImageManager/RegisterImageResponse")
    public Integer registerImage(
        @WebParam(name = "image", targetNamespace = "")
        Image image);

    /**
     * 
     * @param id
     * @return
     *     returns ws.Image
     */
    @WebMethod(operationName = "SearchbyId")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyId", targetNamespace = "http://WS/", className = "ws.SearchbyId")
    @ResponseWrapper(localName = "SearchbyIdResponse", targetNamespace = "http://WS/", className = "ws.SearchbyIdResponse")
    @Action(input = "http://WS/ImageManager/SearchbyIdRequest", output = "http://WS/ImageManager/SearchbyIdResponse")
    public Image searchbyId(
        @WebParam(name = "id", targetNamespace = "")
        int id);

    /**
     * 
     * @param title
     * @return
     *     returns java.util.List<ws.Image>
     */
    @WebMethod(operationName = "SearchbyTitle")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyTitle", targetNamespace = "http://WS/", className = "ws.SearchbyTitle")
    @ResponseWrapper(localName = "SearchbyTitleResponse", targetNamespace = "http://WS/", className = "ws.SearchbyTitleResponse")
    @Action(input = "http://WS/ImageManager/SearchbyTitleRequest", output = "http://WS/ImageManager/SearchbyTitleResponse")
    public List<Image> searchbyTitle(
        @WebParam(name = "title", targetNamespace = "")
        String title);

    /**
     * 
     * @param creaDate
     * @return
     *     returns java.util.List<ws.Image>
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyCreaDate", targetNamespace = "http://WS/", className = "ws.SearchbyCreaDate")
    @ResponseWrapper(localName = "SearchbyCreaDateResponse", targetNamespace = "http://WS/", className = "ws.SearchbyCreaDateResponse")
    @Action(input = "http://WS/ImageManager/SearchbyCreaDateRequest", output = "http://WS/ImageManager/SearchbyCreaDateResponse")
    public List<Image> searchbyCreaDate(
        @WebParam(name = "creaDate", targetNamespace = "")
        String creaDate);

    /**
     * 
     * @param keywords
     * @return
     *     returns java.util.List<ws.Image>
     */
    @WebMethod(operationName = "SearchbyKeywords")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyKeywords", targetNamespace = "http://WS/", className = "ws.SearchbyKeywords")
    @ResponseWrapper(localName = "SearchbyKeywordsResponse", targetNamespace = "http://WS/", className = "ws.SearchbyKeywordsResponse")
    @Action(input = "http://WS/ImageManager/SearchbyKeywordsRequest", output = "http://WS/ImageManager/SearchbyKeywordsResponse")
    public List<Image> searchbyKeywords(
        @WebParam(name = "keywords", targetNamespace = "")
        String keywords);

    /**
     * 
     * @param author
     * @return
     *     returns java.util.List<ws.Image>
     */
    @WebMethod(operationName = "SearchbyAuthor")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyAuthor", targetNamespace = "http://WS/", className = "ws.SearchbyAuthor")
    @ResponseWrapper(localName = "SearchbyAuthorResponse", targetNamespace = "http://WS/", className = "ws.SearchbyAuthorResponse")
    @Action(input = "http://WS/ImageManager/SearchbyAuthorRequest", output = "http://WS/ImageManager/SearchbyAuthorResponse")
    public List<Image> searchbyAuthor(
        @WebParam(name = "author", targetNamespace = "")
        String author);

    /**
     * 
     * @param fileName
     * @return
     *     returns byte[]
     * @throws IOException_Exception
     */
    @WebMethod(operationName = "DownloadImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "DownloadImage", targetNamespace = "http://WS/", className = "ws.DownloadImage")
    @ResponseWrapper(localName = "DownloadImageResponse", targetNamespace = "http://WS/", className = "ws.DownloadImageResponse")
    @Action(input = "http://WS/ImageManager/DownloadImageRequest", output = "http://WS/ImageManager/DownloadImageResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://WS/ImageManager/DownloadImage/Fault/IOException")
    })
    public byte[] downloadImage(
        @WebParam(name = "fileName", targetNamespace = "")
        String fileName)
        throws IOException_Exception
    ;

    /**
     * 
     * @param fileName
     * @param fileContent
     * @return
     *     returns java.lang.Integer
     * @throws IOException_Exception
     */
    @WebMethod(operationName = "UploadImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "UploadImage", targetNamespace = "http://WS/", className = "ws.UploadImage")
    @ResponseWrapper(localName = "UploadImageResponse", targetNamespace = "http://WS/", className = "ws.UploadImageResponse")
    @Action(input = "http://WS/ImageManager/UploadImageRequest", output = "http://WS/ImageManager/UploadImageResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://WS/ImageManager/UploadImage/Fault/IOException")
    })
    public Integer uploadImage(
        @WebParam(name = "fileContent", targetNamespace = "")
        byte[] fileContent,
        @WebParam(name = "fileName", targetNamespace = "")
        String fileName)
        throws IOException_Exception
    ;

    /**
     * 
     * @param fileName
     * @return
     *     returns java.lang.Integer
     * @throws IOException_Exception
     */
    @WebMethod(operationName = "RemoveImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "RemoveImage", targetNamespace = "http://WS/", className = "ws.RemoveImage")
    @ResponseWrapper(localName = "RemoveImageResponse", targetNamespace = "http://WS/", className = "ws.RemoveImageResponse")
    @Action(input = "http://WS/ImageManager/RemoveImageRequest", output = "http://WS/ImageManager/RemoveImageResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://WS/ImageManager/RemoveImage/Fault/IOException")
    })
    public Integer removeImage(
        @WebParam(name = "fileName", targetNamespace = "")
        String fileName)
        throws IOException_Exception
    ;

}