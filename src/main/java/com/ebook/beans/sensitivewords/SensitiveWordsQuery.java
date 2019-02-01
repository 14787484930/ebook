package com.ebook.beans.sensitivewords;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;

@Data
public class SensitiveWordsQuery extends BaseQuery {
    private String id;
    private String word;
}
