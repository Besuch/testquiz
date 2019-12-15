
export class Report{
    constructor(quizId, questionID, option, isQuizSubmited, email){
        this.quizId = quizId;
        this.questionID = questionID;
        this.option = option; //array of checked answers
        this.isQuizSubmited = isQuizSubmited;
        this.email = email;
    }
}