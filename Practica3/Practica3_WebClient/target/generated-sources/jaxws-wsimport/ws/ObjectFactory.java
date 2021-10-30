
package ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UploadImageResponse_QNAME = new QName("http://WS/", "UploadImageResponse");
    private final static QName _DeleteImage_QNAME = new QName("http://WS/", "DeleteImage");
    private final static QName _GetMaxIdResponse_QNAME = new QName("http://WS/", "GetMaxIdResponse");
    private final static QName _RegisterImageResponse_QNAME = new QName("http://WS/", "RegisterImageResponse");
    private final static QName _ModifyImage_QNAME = new QName("http://WS/", "ModifyImage");
    private final static QName _SearchbyAuthor_QNAME = new QName("http://WS/", "SearchbyAuthor");
    private final static QName _DeleteImageResponse_QNAME = new QName("http://WS/", "DeleteImageResponse");
    private final static QName _SearchbyCreaDate_QNAME = new QName("http://WS/", "SearchbyCreaDate");
    private final static QName _ListImages_QNAME = new QName("http://WS/", "ListImages");
    private final static QName _DownloadImageResponse_QNAME = new QName("http://WS/", "DownloadImageResponse");
    private final static QName _UploadImage_QNAME = new QName("http://WS/", "UploadImage");
    private final static QName _RegisterImage_QNAME = new QName("http://WS/", "RegisterImage");
    private final static QName _SearchbyIdResponse_QNAME = new QName("http://WS/", "SearchbyIdResponse");
    private final static QName _CreateUserResponse_QNAME = new QName("http://WS/", "CreateUserResponse");
    private final static QName _CheckPasswordResponse_QNAME = new QName("http://WS/", "CheckPasswordResponse");
    private final static QName _DownloadImage_QNAME = new QName("http://WS/", "DownloadImage");
    private final static QName _SearchbyTitleResponse_QNAME = new QName("http://WS/", "SearchbyTitleResponse");
    private final static QName _ListImagesResponse_QNAME = new QName("http://WS/", "ListImagesResponse");
    private final static QName _RemoveImageResponse_QNAME = new QName("http://WS/", "RemoveImageResponse");
    private final static QName _SearchbyId_QNAME = new QName("http://WS/", "SearchbyId");
    private final static QName _CheckUser_QNAME = new QName("http://WS/", "CheckUser");
    private final static QName _RemoveImage_QNAME = new QName("http://WS/", "RemoveImage");
    private final static QName _CreateUser_QNAME = new QName("http://WS/", "CreateUser");
    private final static QName _IOException_QNAME = new QName("http://WS/", "IOException");
    private final static QName _GetMaxId_QNAME = new QName("http://WS/", "GetMaxId");
    private final static QName _SearchbyKeywords_QNAME = new QName("http://WS/", "SearchbyKeywords");
    private final static QName _SearchbyAuthorResponse_QNAME = new QName("http://WS/", "SearchbyAuthorResponse");
    private final static QName _SearchbyTitle_QNAME = new QName("http://WS/", "SearchbyTitle");
    private final static QName _ModifyImageResponse_QNAME = new QName("http://WS/", "ModifyImageResponse");
    private final static QName _CheckPassword_QNAME = new QName("http://WS/", "CheckPassword");
    private final static QName _CheckUserResponse_QNAME = new QName("http://WS/", "CheckUserResponse");
    private final static QName _SearchbyKeywordsResponse_QNAME = new QName("http://WS/", "SearchbyKeywordsResponse");
    private final static QName _SearchbyCreaDateResponse_QNAME = new QName("http://WS/", "SearchbyCreaDateResponse");
    private final static QName _UploadImageFileContent_QNAME = new QName("", "fileContent");
    private final static QName _DownloadImageResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UploadImageResponse }
     * 
     */
    public UploadImageResponse createUploadImageResponse() {
        return new UploadImageResponse();
    }

    /**
     * Create an instance of {@link DeleteImage }
     * 
     */
    public DeleteImage createDeleteImage() {
        return new DeleteImage();
    }

    /**
     * Create an instance of {@link RegisterImageResponse }
     * 
     */
    public RegisterImageResponse createRegisterImageResponse() {
        return new RegisterImageResponse();
    }

    /**
     * Create an instance of {@link GetMaxIdResponse }
     * 
     */
    public GetMaxIdResponse createGetMaxIdResponse() {
        return new GetMaxIdResponse();
    }

    /**
     * Create an instance of {@link SearchbyAuthor }
     * 
     */
    public SearchbyAuthor createSearchbyAuthor() {
        return new SearchbyAuthor();
    }

    /**
     * Create an instance of {@link ModifyImage }
     * 
     */
    public ModifyImage createModifyImage() {
        return new ModifyImage();
    }

    /**
     * Create an instance of {@link DeleteImageResponse }
     * 
     */
    public DeleteImageResponse createDeleteImageResponse() {
        return new DeleteImageResponse();
    }

    /**
     * Create an instance of {@link SearchbyCreaDate }
     * 
     */
    public SearchbyCreaDate createSearchbyCreaDate() {
        return new SearchbyCreaDate();
    }

    /**
     * Create an instance of {@link ListImages }
     * 
     */
    public ListImages createListImages() {
        return new ListImages();
    }

    /**
     * Create an instance of {@link DownloadImageResponse }
     * 
     */
    public DownloadImageResponse createDownloadImageResponse() {
        return new DownloadImageResponse();
    }

    /**
     * Create an instance of {@link UploadImage }
     * 
     */
    public UploadImage createUploadImage() {
        return new UploadImage();
    }

    /**
     * Create an instance of {@link RegisterImage }
     * 
     */
    public RegisterImage createRegisterImage() {
        return new RegisterImage();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link SearchbyIdResponse }
     * 
     */
    public SearchbyIdResponse createSearchbyIdResponse() {
        return new SearchbyIdResponse();
    }

    /**
     * Create an instance of {@link CheckPasswordResponse }
     * 
     */
    public CheckPasswordResponse createCheckPasswordResponse() {
        return new CheckPasswordResponse();
    }

    /**
     * Create an instance of {@link DownloadImage }
     * 
     */
    public DownloadImage createDownloadImage() {
        return new DownloadImage();
    }

    /**
     * Create an instance of {@link SearchbyTitleResponse }
     * 
     */
    public SearchbyTitleResponse createSearchbyTitleResponse() {
        return new SearchbyTitleResponse();
    }

    /**
     * Create an instance of {@link ListImagesResponse }
     * 
     */
    public ListImagesResponse createListImagesResponse() {
        return new ListImagesResponse();
    }

    /**
     * Create an instance of {@link RemoveImageResponse }
     * 
     */
    public RemoveImageResponse createRemoveImageResponse() {
        return new RemoveImageResponse();
    }

    /**
     * Create an instance of {@link SearchbyId }
     * 
     */
    public SearchbyId createSearchbyId() {
        return new SearchbyId();
    }

    /**
     * Create an instance of {@link RemoveImage }
     * 
     */
    public RemoveImage createRemoveImage() {
        return new RemoveImage();
    }

    /**
     * Create an instance of {@link CheckUser }
     * 
     */
    public CheckUser createCheckUser() {
        return new CheckUser();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link GetMaxId }
     * 
     */
    public GetMaxId createGetMaxId() {
        return new GetMaxId();
    }

    /**
     * Create an instance of {@link SearchbyAuthorResponse }
     * 
     */
    public SearchbyAuthorResponse createSearchbyAuthorResponse() {
        return new SearchbyAuthorResponse();
    }

    /**
     * Create an instance of {@link SearchbyKeywords }
     * 
     */
    public SearchbyKeywords createSearchbyKeywords() {
        return new SearchbyKeywords();
    }

    /**
     * Create an instance of {@link SearchbyTitle }
     * 
     */
    public SearchbyTitle createSearchbyTitle() {
        return new SearchbyTitle();
    }

    /**
     * Create an instance of {@link CheckPassword }
     * 
     */
    public CheckPassword createCheckPassword() {
        return new CheckPassword();
    }

    /**
     * Create an instance of {@link ModifyImageResponse }
     * 
     */
    public ModifyImageResponse createModifyImageResponse() {
        return new ModifyImageResponse();
    }

    /**
     * Create an instance of {@link CheckUserResponse }
     * 
     */
    public CheckUserResponse createCheckUserResponse() {
        return new CheckUserResponse();
    }

    /**
     * Create an instance of {@link SearchbyKeywordsResponse }
     * 
     */
    public SearchbyKeywordsResponse createSearchbyKeywordsResponse() {
        return new SearchbyKeywordsResponse();
    }

    /**
     * Create an instance of {@link SearchbyCreaDateResponse }
     * 
     */
    public SearchbyCreaDateResponse createSearchbyCreaDateResponse() {
        return new SearchbyCreaDateResponse();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "UploadImageResponse")
    public JAXBElement<UploadImageResponse> createUploadImageResponse(UploadImageResponse value) {
        return new JAXBElement<UploadImageResponse>(_UploadImageResponse_QNAME, UploadImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "DeleteImage")
    public JAXBElement<DeleteImage> createDeleteImage(DeleteImage value) {
        return new JAXBElement<DeleteImage>(_DeleteImage_QNAME, DeleteImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMaxIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "GetMaxIdResponse")
    public JAXBElement<GetMaxIdResponse> createGetMaxIdResponse(GetMaxIdResponse value) {
        return new JAXBElement<GetMaxIdResponse>(_GetMaxIdResponse_QNAME, GetMaxIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "RegisterImageResponse")
    public JAXBElement<RegisterImageResponse> createRegisterImageResponse(RegisterImageResponse value) {
        return new JAXBElement<RegisterImageResponse>(_RegisterImageResponse_QNAME, RegisterImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "ModifyImage")
    public JAXBElement<ModifyImage> createModifyImage(ModifyImage value) {
        return new JAXBElement<ModifyImage>(_ModifyImage_QNAME, ModifyImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyAuthor")
    public JAXBElement<SearchbyAuthor> createSearchbyAuthor(SearchbyAuthor value) {
        return new JAXBElement<SearchbyAuthor>(_SearchbyAuthor_QNAME, SearchbyAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "DeleteImageResponse")
    public JAXBElement<DeleteImageResponse> createDeleteImageResponse(DeleteImageResponse value) {
        return new JAXBElement<DeleteImageResponse>(_DeleteImageResponse_QNAME, DeleteImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreaDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyCreaDate")
    public JAXBElement<SearchbyCreaDate> createSearchbyCreaDate(SearchbyCreaDate value) {
        return new JAXBElement<SearchbyCreaDate>(_SearchbyCreaDate_QNAME, SearchbyCreaDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "ListImages")
    public JAXBElement<ListImages> createListImages(ListImages value) {
        return new JAXBElement<ListImages>(_ListImages_QNAME, ListImages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "DownloadImageResponse")
    public JAXBElement<DownloadImageResponse> createDownloadImageResponse(DownloadImageResponse value) {
        return new JAXBElement<DownloadImageResponse>(_DownloadImageResponse_QNAME, DownloadImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "UploadImage")
    public JAXBElement<UploadImage> createUploadImage(UploadImage value) {
        return new JAXBElement<UploadImage>(_UploadImage_QNAME, UploadImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "RegisterImage")
    public JAXBElement<RegisterImage> createRegisterImage(RegisterImage value) {
        return new JAXBElement<RegisterImage>(_RegisterImage_QNAME, RegisterImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyIdResponse")
    public JAXBElement<SearchbyIdResponse> createSearchbyIdResponse(SearchbyIdResponse value) {
        return new JAXBElement<SearchbyIdResponse>(_SearchbyIdResponse_QNAME, SearchbyIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "CreateUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "CheckPasswordResponse")
    public JAXBElement<CheckPasswordResponse> createCheckPasswordResponse(CheckPasswordResponse value) {
        return new JAXBElement<CheckPasswordResponse>(_CheckPasswordResponse_QNAME, CheckPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "DownloadImage")
    public JAXBElement<DownloadImage> createDownloadImage(DownloadImage value) {
        return new JAXBElement<DownloadImage>(_DownloadImage_QNAME, DownloadImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyTitleResponse")
    public JAXBElement<SearchbyTitleResponse> createSearchbyTitleResponse(SearchbyTitleResponse value) {
        return new JAXBElement<SearchbyTitleResponse>(_SearchbyTitleResponse_QNAME, SearchbyTitleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "ListImagesResponse")
    public JAXBElement<ListImagesResponse> createListImagesResponse(ListImagesResponse value) {
        return new JAXBElement<ListImagesResponse>(_ListImagesResponse_QNAME, ListImagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "RemoveImageResponse")
    public JAXBElement<RemoveImageResponse> createRemoveImageResponse(RemoveImageResponse value) {
        return new JAXBElement<RemoveImageResponse>(_RemoveImageResponse_QNAME, RemoveImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyId")
    public JAXBElement<SearchbyId> createSearchbyId(SearchbyId value) {
        return new JAXBElement<SearchbyId>(_SearchbyId_QNAME, SearchbyId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "CheckUser")
    public JAXBElement<CheckUser> createCheckUser(CheckUser value) {
        return new JAXBElement<CheckUser>(_CheckUser_QNAME, CheckUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "RemoveImage")
    public JAXBElement<RemoveImage> createRemoveImage(RemoveImage value) {
        return new JAXBElement<RemoveImage>(_RemoveImage_QNAME, RemoveImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "CreateUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMaxId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "GetMaxId")
    public JAXBElement<GetMaxId> createGetMaxId(GetMaxId value) {
        return new JAXBElement<GetMaxId>(_GetMaxId_QNAME, GetMaxId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyKeywords")
    public JAXBElement<SearchbyKeywords> createSearchbyKeywords(SearchbyKeywords value) {
        return new JAXBElement<SearchbyKeywords>(_SearchbyKeywords_QNAME, SearchbyKeywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyAuthorResponse")
    public JAXBElement<SearchbyAuthorResponse> createSearchbyAuthorResponse(SearchbyAuthorResponse value) {
        return new JAXBElement<SearchbyAuthorResponse>(_SearchbyAuthorResponse_QNAME, SearchbyAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyTitle")
    public JAXBElement<SearchbyTitle> createSearchbyTitle(SearchbyTitle value) {
        return new JAXBElement<SearchbyTitle>(_SearchbyTitle_QNAME, SearchbyTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "ModifyImageResponse")
    public JAXBElement<ModifyImageResponse> createModifyImageResponse(ModifyImageResponse value) {
        return new JAXBElement<ModifyImageResponse>(_ModifyImageResponse_QNAME, ModifyImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "CheckPassword")
    public JAXBElement<CheckPassword> createCheckPassword(CheckPassword value) {
        return new JAXBElement<CheckPassword>(_CheckPassword_QNAME, CheckPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "CheckUserResponse")
    public JAXBElement<CheckUserResponse> createCheckUserResponse(CheckUserResponse value) {
        return new JAXBElement<CheckUserResponse>(_CheckUserResponse_QNAME, CheckUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyKeywordsResponse")
    public JAXBElement<SearchbyKeywordsResponse> createSearchbyKeywordsResponse(SearchbyKeywordsResponse value) {
        return new JAXBElement<SearchbyKeywordsResponse>(_SearchbyKeywordsResponse_QNAME, SearchbyKeywordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreaDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "SearchbyCreaDateResponse")
    public JAXBElement<SearchbyCreaDateResponse> createSearchbyCreaDateResponse(SearchbyCreaDateResponse value) {
        return new JAXBElement<SearchbyCreaDateResponse>(_SearchbyCreaDateResponse_QNAME, SearchbyCreaDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fileContent", scope = UploadImage.class)
    public JAXBElement<byte[]> createUploadImageFileContent(byte[] value) {
        return new JAXBElement<byte[]>(_UploadImageFileContent_QNAME, byte[].class, UploadImage.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = DownloadImageResponse.class)
    public JAXBElement<byte[]> createDownloadImageResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_DownloadImageResponseReturn_QNAME, byte[].class, DownloadImageResponse.class, ((byte[]) value));
    }

}
