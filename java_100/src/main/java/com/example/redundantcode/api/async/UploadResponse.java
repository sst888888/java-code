package com.example.redundantcode.api.async;

import lombok.Data;

@Data
public class UploadResponse {
    private String downloadUrl;
    private String thumbnailDownloadUrl;
}
