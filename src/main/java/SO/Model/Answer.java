package SO.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Answer {

	public Answer() {
	}

	public Answer(String content, short raiting) {
		this.content = content;
		this.raiting = raiting;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int answerId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User author;
	@ManyToOne
	@JoinColumn(name = "questionId")
	private Question question;

	private String content;
	private Date dateOfComment;
	private short raiting;

	@ManyToOne(fetch = FetchType.LAZY)
	private Answer answer;

	@OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Answer> answers = new HashSet<>();

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateOfComment() {
		return dateOfComment;
	}

	public void setDateOfComment(Date dateOfComment) {
		this.dateOfComment = dateOfComment;
	}

	public short getRaiting() {
		return raiting;
	}

	public void setRaiting(short raiting) {
		this.raiting = raiting;
	}

//	public Set<Answer> getReplayComment() {
//		return replayComment;
//	}
//
//	public void setReplayComment(Set<Answer> replayComment) {
//		this.replayComment = replayComment;
//	}

}
