package com.qingshuge.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.PdfDocument;
import com.qingshuge.bean.Book;
import com.qingshuge.bean.Image;
import com.qingshuge.dao.BookMapper;
import com.qingshuge.service.FileService;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.apache.tika.Tika;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//import javax.annotation.Resource;

@RestController
public class FileUploadController {

//    @Autowired
//    private BookMapper fileUploadMapper;

    @Autowired
    BookMapper bookMapper;

//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) {
//        // 获取文件原始名
//        String originalFilename = file.getOriginalFilename();
//        // 获取文件后缀名
//        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
//        // 生成新的文件名
//        String newFileName = UUID.randomUUID().toString() + suffixName;
//        // 构建文件存储路径
//        File destFile = new File("C:\\Users\\Huawei\\Desktop\\img\\" + newFileName);
//        try {
//            // 将文件保存到指定路径
//            file.transferTo(destFile);
//            // 将文件信息插入到数据库
//            Book book = new Book();
//            book.setBook_id(UUID.randomUUID().toString());
//            book.setBookname(originalFilename);
//            book.setBook_path(destFile.getAbsolutePath());
//            book.setUser_id(id);
//            bookMapper.insertFile(book.getBook_id(),book.getUser_id(),book.getBookname(),book.getBook_path());
//            return "上传成功";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "上传失败";
//    }



// ...

    @PostMapping("/uploadBook")
    public int uploadBook(@RequestParam("brief_book") String brief_book, @RequestParam("bookname") String bookname, @RequestParam("user_id") int user_id, @RequestParam("tag") String tag) {
        Book book = new Book();
        book.setBrief_book(brief_book);
        book.setBookname(bookname);
        book.setUser_id(user_id);
        book.setTag(tag);

        int uploadBook = bookMapper.uploadBook(brief_book,bookname,user_id,tag);
        int test = book.getBook_id();
//        System.out.println(test);
        System.out.println(uploadBook);
        return uploadBook;
    }



    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("bookname") String bookname, @RequestParam("id") int id,@RequestParam("tag") String tag) {
        // 获取文件原始名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 生成新的文件名
        String newFileName = UUID.randomUUID().toString() + suffixName;
        // 构建文件存储路径
        File destFile = new File("C:\\Users\\Huawei\\Desktop\\doc\\" + newFileName);
        try {
            // 将文件保存到指定路径
            file.transferTo(destFile);
            // 将文件信息插入到数据库
            Book book = new Book();
//            book.setBook_id(UUID.randomUUID().toString());
            book.setBookname(bookname);
            book.setBook_path(destFile.getAbsolutePath());
            book.setUser_id(id);
            book.setTag(tag);
            Tika tika = new Tika();
            String fileType = tika.detect(destFile);
            book.setFileType(fileType);
            bookMapper.insertFile( book.getUser_id(), book.getBookname(), book.getBook_path(), book.getFileType(),book.getTag());
//            bookMapper.insertFile(book.getUser_id(), book.getBookname(), book.getBook_path(), book.getFileType());
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }


    @GetMapping("/file/{book_id}")
    public Book getFileById(@PathVariable("book_id") String book_id) {
        return bookMapper.selectFileById(book_id);
    }



    @Autowired
    private FileService fileService; // 获取文件URL的服务

    @GetMapping(value = "/pdf/{fileId}")
    public ResponseEntity<byte[]> getPdf(@PathVariable("fileId") String fileId) throws IOException {
        // 获取文件URL
        String fileUrl = fileService.getFileUrl(fileId);
        if (fileUrl == null) {
            return ResponseEntity.notFound().build();
        }
        // 下载文件到本地
        URL url = new URL(fileUrl);
        InputStream inputStream = url.openStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        byte[] bytes = outputStream.toByteArray();
        // 返回响应
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "file.pdf");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public FileUploadController(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @RequestMapping("indexBook")
    public int indexPices(@RequestParam("bookname") String bookname,
                          @RequestParam("user_id") int user_id,
                          @RequestParam("tag") String tag,
                          @RequestParam("brief_book") String brief_book) throws Exception{
        Book book = new Book();
        book.setBookname(bookname);
        book.setUser_id(user_id);
        book.setTag(tag);
        book.setBrief_book(brief_book);


        IndexRequest indexRequest = new IndexRequest("book");
        indexRequest.id(Integer.toString(user_id))
                .source(new ObjectMapper().writeValueAsString(book), XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.status());

        return 1;
    }

    @RequestMapping("/updateBookPic")
    public int updateBookPic(@RequestParam("book_id") int book_id ,@RequestParam("pic_id") int pic_id ) {

        int updateBookPic = bookMapper.updateBookPic(book_id,pic_id);
        System.out.println(updateBookPic);
        return updateBookPic;
    }

}