package model;
public class Comment {
	protected int commentId;
	protected String content;
	protected int barberId;
	protected int customerId;
	
	public Comment( String Content, int BarberId, int CustomerId){
		this.content = Content;
		this.barberId = BarberId;
		this.customerId = CustomerId;
	}
	
	public Comment(int CId, String Content, int BarberId, int CustomerId){
		this.commentId = CId;
		this.content = Content;
		this.barberId = BarberId;
		this.customerId = CustomerId;
	}
	
	public Comment(int CId){
		this.commentId = CId;
	}
	
	public int getCommentId(){
		return commentId;
	}
	public void setCommentId(int c){
		this.commentId = c;
	}
	
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	
	public int getBarberId(){
		return barberId;
	}
	public void setBarberId(int id){
		this.barberId = id;
	}
	
	public int getCustomerId(){
		return customerId;
	}
	public void setCustomerId(int cid){
		this.customerId = cid;
	}
	
	

}
