package com.example.redundantcode.api.async;

import lombok.Data;

@Data
public class SyncUploadResponse {
    private String downloadUrl;
    private String thumbnailDownloadUrl;
}
