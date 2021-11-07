package data.game;

import java.io.Serializable;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class QuizGame extends Game implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    public static String MCQName = "multiple-choice question";
    public static String MRQName = "multiple-response question";
    public static String TFQName = " true-false question";
    public static String FillInBlankName = "fill-in-the-blanks question";
    public static final Random RANDOM = new Random();
    public static final int NumberOfQuestions = 4;
    public static int NumOfCorrect = 0;
    public static int NumOfUserAnswer = 0;
    public String level;
    private static int startID = 41;
    private static int startIDDiff = 51;

    public QuizGame(String level) {
        this.level = level;
        NumOfCorrect = 0;
        NumOfUserAnswer = 0;
    }

    @Override
    public int execute(boolean isEasy) {
        System.out.println("Welcome to quiz game. "
                + "You must get all correct answers of four questions to win the card and pass this challenge!");
        boolean isWin = false;   // changed
        if (isEasy) {
            while (NumOfCorrect < 4) {
                switch (NumOfUserAnswer % 4) {
                case 0:
                    System.out.println("1. MCQ (answer format: eg. A)");
                    getQuestion(MCQ, MCQAnswers);
                    break;
                case 1:
                    System.out.println("2. MRQ (answer format: eg. ABD [please input in alphabetical order])");
                    getQuestion(MRQ, MRQAnswers);
                    break;
                case 2:
                    System.out.println("3. TFQ (answer format: eg. False)");
                    getQuestion(TFQ, TFQAnswers);
                    break;
                case 3:
                    System.out.println("4. FIB (answer format: "
                            + "[if the answer is a verb, just -ing format] "
                            + "eg. looking [all the characters should be lower case])");
                    getQuestion(FIB, FIBAnswers);
                    break;
                default:
                    System.out.println("Error");
                }
                if (NumOfUserAnswer == 4 && NumOfCorrect == 4) {
                    isWin = true;   //added
                    System.out.println("Good! You achieve full mark.\n");
                    break;
                } else if (NumOfUserAnswer == 4 && NumOfCorrect < 4) {
                    System.out.println("Sorry! You did not pass this challenge.\n");
                    NumOfCorrect = 0;
                    NumOfUserAnswer = 0;
                    break;
                }
            }
            System.out.println("end");
            return isWin ? startID : 0;   // added
        } else {
            while (NumOfCorrect < 4) {
                switch (NumOfUserAnswer % 4) {
                case 0:
                    System.out.println("1. MCQ (answer format: eg. A)");
                    getQuestion(MCQD, MCQDAnswers);
                    break;
                case 1:
                    System.out.println("2. MRQ (answer format: eg. ABD [please input in alphabetical order])");
                    getQuestion(MRQD, MRQDAnswers);
                    break;
                case 2:
                    System.out.println("3. TFQ (answer format: eg. False)");
                    getQuestion(TFQD, TFQDAnswers);
                    break;
                case 3:
                    System.out.println("4. FIB (answer format: "
                        + "[if the answer is a verb, just -ing format] "
                        + "eg. looking [all the characters should be lower case])");
                    getQuestion(FIBD, FIBDAnswers);
                    break;
                default:
                    System.out.println("Error");
                }
                if (NumOfUserAnswer == 4 && NumOfCorrect == 4) {
                    isWin = true;   //added
                    System.out.println("Good! You achieve full mark.\n");
                    break;
                } else if (NumOfUserAnswer == 4 && NumOfCorrect < 4) {
                    System.out.println("Sorry! You did not pass this challenge.\n");
                    NumOfCorrect = 0;
                    break;
                }
            }
            System.out.println("end");
            return isWin ? startIDDiff : 0;   // added
        }
    }

    public void getQuestion(String[] questions, String[] answers) {
        int index = chooseQuestion(questions);
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        answer = answer.replace("(","");
        answer = answer.replace(")","");
        answer = answer.replace("[","");
        answer = answer.replace("]","");
        if (answer.toLowerCase().equals(answers[index].toLowerCase())) {
            System.out.println("Congratulations! The answer is correct!\n");
            NumOfCorrect++;
        } else {
            System.out.println("Oops! The answer is not correct.");
            System.out.println("Correct answer: " + answers[index] + "\n");
        }
        NumOfUserAnswer++;
    }

    public int chooseQuestion(String[] questions) {
        int index = RANDOM.nextInt(questions.length);
        System.out.println(questions[index]);
        return index;
    }

    @Override
    public String getName() {
        return "Knowledge Quiz";
    }

    public static String[] MCQ = {"Who is the person that writes code and communicates instructions to a computer? "
            + "\n[A: Code][B: Programmer][C: Command][D: Algorithm]",
        "What is the action of finding and fixing errors or mistakes in programs? "
                + "\n[A: Looping][B: Sequencing][C: Debugging][D: Decomposing]",
        "What is an error or mistake called in a program? "
                + "\n[A: Bug][B: Program][C: Code][D: Loop]",
        "Who invented Java language? \n[A: Jackie Chan][B: Linus Torvalds][C: Fabrice Bellard][D: James Gosling]",
        "Of the following, if statements, which one returns true?"
                + "\n[A: X = 1; X > 0;][B: X = \"hi\"; X == \"hello\";][C: X = 0; X == 1;][D: X = \"k\"; X == 1]"};

    public static String[] MRQ = {"Which will the following statement return true?"
            + "\n[A: 1 == 1][B: \"abc\" == \"bcd\"][C: \"Hello\" == \"hello\"][D: \"Hello\" == \"Hello\"]",
        "Choose all programming languages.\n[A: Python][B: Chinese][C: Java][D: C-+]",
        "Choose all the correct codes. \n[A: 1 = x];[B: x++;][C: return x;][D: return y]",
        "Choose correct statements which satisfy Java coding style."
                + "\n[A: x=1][B: int a[] = new int[20];][C: int[] a = new int[20];][D: x = 2]",
        "Choose all Java Language Keywords. \n[A: continue][B: final][C: hello][D: private]"};

    public static String[] TFQ = {"Is \"R\" a programming language? \n[True][False]",
        "Can \"for\" and \"while\" both be used to form a loop? \n[True][False]",
        "\"hello\" is a Java Language Keywords. \n[True][False]"};

    public static String[] FIB = {"What loop can loop endlessly?",
        "Which integer number represents false in C language?",
        "______ is the process of finding errors and fixing them within a program.",
        "______ defines the common variables and methods of a set of objects.",
        "If A=10, then after B=++A, the value of B is _______."};


    public static String[] MCQAnswers = {"B", "C", "A", "D", "A"};
    public static String[] MRQAnswers = {"AD", "AC", "BC", "CD", "ABD"};
    public static String[] TFQAnswers = {"True", "True", "False"};
    public static String[] FIBAnswers = {"infinite loop", "0", "debugging", "class", "11"};

    public static String[] MCQD = {"What is HTML used for?"
            + "\n[A: Create web pages][B: Add style to the document][C: Create music][D: Code games]",
        "What is CSS used for?"
                    + "\n[A: Create web pages][B: Create music][C: Add style to the document][D: Code games]",
        "Let x be an integer which can take a value of 0 or 1. "
                    + "The statement if(x == 0) x = 1; else x = 0; is equivalent to which one of the following?"
                    + "\n[A: x = 1 + x;][B: x = 1 - x;][C: x = x - 1;][D: x = 1 % x;]"};

    public static String[] TFQD = {"Is \"SDLC\" stands for Software Development Life Cycle? \n[True][False]",
        "Both \"BFS\" and \"DFS\" can be used to graph traversal? \n[True][False]",
        "\"do while\" may not necessarily be executed. \n[True][False]"};

    public static String[] MRQD = {"How do you get your website online?(choose all the steps)"
            + "\n[A: Pick a domain name]"
            + "\n[B: Register your domain name]"
            + "\n[C: Choose a web hosting company]\n[D: Upload your website]",
        "Consider the following statement(s) about Java:\n"
                    + "[A: All white-space characters (blanks) are ignored by the compiler.]\n"
                    + "[B: Java keywords can be used as variable names.]\n"
                    + "[C: An identifier does not begin with a digit and does not contain any spaces.]\n"
                    + "[D: The execution of Java applications begins at method main.]\n"
                    + "Which of them is correct?"};

    public static String[] FIBD = {"What does HTML stand for? _______",
        "What does CSS stand for? _______",
        "What does SQL stand for?  _______"};

    public static String[] TFQDAnswers = {"True", "True", "False"};
    public static String[] MCQDAnswers = {"A", "C", "B"};
    public static String[] MRQDAnswers = {"ABCD", "CD"};
    public static String[] FIBDAnswers = {"hypertext markup language", "cascading style sheets",
        "structured query language"};

    public static String[] QuizCards = {"Abstraction, means ignoring the non-essential "
            + "details of an object and concentrating on its essential details.",
        "Maintains classes of the local file system, in a separate namespace.",
        "Avoids execution of the program whose byte code has been changed illegally.",
        "Java is an Object-Oriented Programming (OOP) structure.",
        "Java performs input and output operations using streams.",
        "Prefer returning Empty Collections instead of Null",
        "Use Strings carefully",
        "Avoiding Memory leaks by simple tricks",
        "Avoiding Deadlocks in Java",
        "Interface is Better Than Abstract Class",
        "Better to Avoid BigDecimal and BigInteger"};
}
