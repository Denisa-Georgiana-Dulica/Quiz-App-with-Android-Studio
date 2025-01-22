# ❓💻 Quiz App about the history of telecommunications

## Main Screen - Application Menu
💥 This image shows the application menu, implemented using NavigationDrawerLayout.
The options available in the menu are:
- Start Quiz: Redirects the user to the quiz.
- Progress: Shows the user's progress based on correct answers.
- Learn: Allows questions to be recapped and displayed by difficulty level.
  
💥 The menu design includes:
- A representative icon for each option.
- A custom theme specified in styles.xml.
- The data was taken from the strings.xml file.
![WhatsApp Image 2025-01-22 at 19 07 17 (1)](https://github.com/user-attachments/assets/aea4228e-b395-4f5b-90af-a0154a953586)

## Quiz section
💥 Important features:
- Each question includes three answer options.
- After selecting an answer, the "See the correct answers" button highlights the correct answers in green and the wrong ones in red.
- The interface is intuitive, using visual controls such as: RadioButton for selecting answers, Button for validating answers.
- The Quiz is a ListView used with a custom Adapter.
![WhatsApp Image 2025-01-22 at 19 07 18 (4)](https://github.com/user-attachments/assets/e51fbee2-b37d-42a3-8e55-6434ab9e5a79)

## Learn section 
💥 In this section, the user can see all the questions and the correct answers.
💥 Key Features:
- CheckBox: User can select "See the questions in descending order of difficulty" option to sort the questions from level 3 (hardest) to level 1 (easiest).
- Each question is displayed along with its correct answer.
![WhatsApp Image 2025-01-22 at 19 07 18 (3)](https://github.com/user-attachments/assets/b7272e1a-61c3-4eac-ac78-d2226d5ef28d)

## Progress section
💥 Shows the user's progress in the quiz.
💥 Visual elements used:
- ProgressBar: Represents the user's progress on a scale of 0 to 10 based on correct answers.
![WhatsApp Image 2025-01-22 at 19 07 18 (2)](https://github.com/user-attachments/assets/bc13f03c-721a-4897-bece-97a944cf55ed)

## Adding a new question
💥 This screen allows the user to add new questions to the quiz.
💥 Features:
- TextInputLayout and TextInputEditText for entering the question and the answer options.
- A dedicated field for the correct answer.
- Spinner for selecting the difficulty level of the question (1, 2 or 3).
- Button to save the question.
- Although new questions are dynamically inserted, the application's initial questions are handled in strings.xml
![WhatsApp Image 2025-01-22 at 19 07 18 (1)](https://github.com/user-attachments/assets/20031166-db2e-42af-9f48-23fb418e14dd)

![WhatsApp Image 2025-01-22 at 19 07 18](https://github.com/user-attachments/assets/d2c53bfb-b776-4d39-82e5-52e36df298c2)
