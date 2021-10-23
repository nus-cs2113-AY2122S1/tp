package cooper.forum;

import java.util.ArrayList;

import cooper.exceptions.InvalidForumPostIdException;
import cooper.exceptions.InvalidForumDeleteByNonOwnerException;


public class ForumManager {
    private ArrayList<ForumPost> forumPosts;

    public ForumManager() {
        forumPosts = new ArrayList<ForumPost>();
    }

    public void addPost(String username, String content) {
        forumPosts.add(new ForumPost(username, content));
    }

    public void addComment(String username, String content, int postId)
            throws InvalidForumPostIdException {
        checkValidPostId(postId);
        forumPosts.get(postId).addComment(username, content);
    }

    public String deletePost(String username, int postId) 
            throws InvalidForumPostIdException, InvalidForumDeleteByNonOwnerException {
        checkValidPostId(postId);
        ForumPost post = forumPosts.get(postId);
        if (post.getUsername() == username) {
            // can only delete one's own post
            String content = forumPosts.get(postId).getContent();
            forumPosts.remove(postId);
            return content;
        } else {
            throw new InvalidForumDeleteByNonOwnerException();
        }
    }

    
    public String commentPost(String username, String content, int postId)  
            throws InvalidForumPostIdException {
        checkValidPostId(postId);
        forumPosts.get(postId).addComment(username, content);
        return forumPosts.get(postId).getContent(); // return the original post for Ui
    }

    private void checkValidPostId(int postId) throws InvalidForumPostIdException {
        if (postId >= forumPosts.size() || postId < 0) {
            throw new InvalidForumPostIdException();
        }
    }

}
