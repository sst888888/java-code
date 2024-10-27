package org.cp.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: cp
 * @date: 2024-10-27 16:59
 */
public interface Resource {
    InputStream getInputStream() throws IOException;

}
