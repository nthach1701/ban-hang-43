package com.nnt.banhang.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.*;
import com.google.auth.oauth2.GoogleCredentials;
import org.apache.commons.io.IOUtils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.nnt.banhang.entity.FirebaseCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@Service
public class FireBaseService {

    private  StorageOptions options;

    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount = createFirebaseCredential();
            this.options =  StorageOptions.newBuilder()
                    .setProjectId("banhangspringboot")
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String[] uploadFile(MultipartFile multipartFile) throws IOException {

        File file = convertMultiPartToFile(multipartFile);
        Path filePath = file.toPath();
        String objectName = generateFileName(multipartFile);
        Storage  storage = options.getService();
        BlobId blobId = BlobId.of("banhangspringboot.appspot.com", objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, Files.readAllBytes(filePath));

        return new String[]{"fileUrl", objectName};
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }

    private InputStream createFirebaseCredential() throws Exception {
        FirebaseCredential firebaseCredential = new FirebaseCredential();
        firebaseCredential.setType("service_account");
        firebaseCredential.setProject_id("banhangspringboot");
        firebaseCredential.setPrivate_key_id("de3b5815f7eea538f9acf541541515a24e6869e7");
        firebaseCredential.setPrivate_key("-----BEGIN PRIVATE KEY-----\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCuu4QCfGyd5xc8\naEQ85MKUFNYlyydff1hlEOhNiCpuDlwok12oW5Xz8I+iZ83UnLq/H/w/K84PNNUm\nZHmvCBSCcCuXIZKd3jQtJGS/UQJgp4EgKnveu0++C52ysMH7zRWiKqYbxxboFnA7\n8ze397blIhvCouO/2p28PX1mIAApoR3C2Ys94aBbPsC3z9RkpGloh9ksOBELtkqB\nNM92BSChC/VmJr1RSbAGSMTIKRvfwB9BcyEw4zb9KX2QPT7vccwErvivwU+blH6M\nHolEIAC5CLCZ2Of46p+zv7Ilku8eXtwHVu3KcihY/iXn2xOlnvwDjPoLTvuqvBQO\nDK25K0B9AgMBAAECggEAVlhYfCgDPqZfL50wt0yuMKH5TvTMsJ7LzimkOggDO56C\nARUNj7Aucjzapb0ZFCsdB2xlaMwbSB1qBU5aaVmbVPfcOpM/Eh8ndnVJCdeczqmH\noB8OVjsGxVmVJKO/QFCQk7HyosBnu+m2rZFbY+AX87O+txhqZjjAgLB8IrbGjcr6\n4yp15DTifrLAW72rLNWHZVNhtppFwEfjgW+k2hwOtEIYhR2XvJOxEHdwlkuZpJEU\nEETzxCuov2PHYshCmAgEpnKE+sNJEkB08qSp5s5hgIcu4nx1tQdtahezvTv3r6wQ\nCgx3J+q2pEjfiNOdOZw6CfW5+fOYC9xLsELumf7B6wKBgQDyWO0n0ySG8XzVXHQ6\n69G4n6it3H+sqHv0q1+OLgMRljcvhlqU7EsU/JnWAcVP90iPqNJAXIqLEjFbQ5q1\nfqRlmIVM3AHtUPN9ZD/bqrbjVQYNfOciHEvWqgHNL2Uf5dkqe/0+9dybMNs6nAeG\nqzlPz+E8YlYaX7WifMXfocQsewKBgQC4k3bT/9UUrG2ocm18FgD7qOka7MjFoLPC\nG7vWwStNqlXQ722kP8SBIfR0a0ktfLopW2nrhxHlljPF95khlNvsdcYNSGh35RyW\nDZ4BP2+uZYLAIE4gdFv0cWhx85/C5mzd7sXGblhK+2Qtq8X4SqGHxnHPrXUkoVaw\nQ4Qx0IyhZwKBgGGRfcFH12/gr2Jy+3RxVpje5plAqcJIfscta6YhTij5cjRXAgbs\n7vl/DSxEwDyGkRcNX8B7wWdmJqrYl+O0HdmtQxWDid8SdUUpZDA7FbCGnY42mBN6\nkunUvi7ugucJsZnSG04KzAtK6yg4gysrqkJAriySZpntqIHHfB6h0QobAoGAMsJj\nEGQvzjKpKYTmdRR8Crll+cCaajcKmFwWKTYQWZh5Zy8hIOVkffq/hnCmvDqr24nH\nND19/3xyGoQ/+Ys9e+bbo3WBPU+lr+JozIEMjpJeBcYcljJJw4yYo5XTHr9Nuy7Z\n9vsLjvtZjnHJzMGBcJxzWiCisYMpGupGZYir1AsCgYBHVbe3F+BYLYpbMOiVHV5h\n/XfjzMYS0dDuZ6wQoGJK0rAfpyfgfXOaTyMsNnzrI0zSSpn3c2mIsvsG59Enz2vx\nRVxOiR13bqdSD/V+kGXfm8GRmn/4740osSc2TJq+6QhZbtjp9ILkvWuJkAeTwVzY\nc/McdpaGwiLh9TQrlBsJLg==\n-----END PRIVATE KEY-----\n");
        firebaseCredential.setClient_email("firebase-adminsdk-7ri5r@banhangspringboot.iam.gserviceaccount.com");
        firebaseCredential.setClient_id("116783704775492515950");
        firebaseCredential.setAuth_uri("https://accounts.google.com/o/oauth2/auth");
        firebaseCredential.setToken_uri("https://oauth2.googleapis.com/token");
        firebaseCredential.setAuth_provider_x509_cert_url("https://www.googleapis.com/oauth2/v1/certs");
        firebaseCredential.setClient_x509_cert_url("https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-7ri5r%40banhangspringboot.iam.gserviceaccount.com");

        //serialize with Jackson
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(firebaseCredential);

        //convert jsonString string to InputStream using Apache Commons
        return IOUtils.toInputStream(jsonString);
    }
}
