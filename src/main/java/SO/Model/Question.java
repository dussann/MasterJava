package SO.Model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
public class Question {

	public Question() {
	}

	public Question(String header, String content) {
		this.header = header;
		this.content = content;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question-generator")
	@SequenceGenerator(name = "question-generator",sequenceName = "ques_seq")
	private int questionId;
	private String header;
	private String content;

	@ManyToOne	
	@JoinColumn(name = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@OneToMany(mappedBy = "question")
	private Set<Answer> answers;

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
