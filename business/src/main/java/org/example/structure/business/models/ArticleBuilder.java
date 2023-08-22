package org.example.structure.business.models;

public class ArticleBuilder {
    private ArticleBuilder() {
        throw new UnsupportedOperationException("ArticleBuilder is a utility class and cannot be instantiated");
    }
    public static ArticleStep get(){
        return new ArticleApi();
    }
}
