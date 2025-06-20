package Controller;

import Model.Author.Author;
import Model.Author.AuthorOps;
public class AuthorController {

    public static Result searchAuthor(String authorName) {
        Author author = AuthorOps.searchAuthor(authorName);
        Result result = new Result("error", "Author not found", -1);
        result.setData(author);
        if (author != null) {
            result.status = "success";
            result.message = "Author found";
            return result;
        } else {
            return result;
        }
    }
}
