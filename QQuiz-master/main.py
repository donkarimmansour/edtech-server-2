from openai import OpenAI
import os
from dotenv import load_dotenv
from fastapi import FastAPI, HTTPException
import uvicorn
import logging
from pydantic import BaseModel
from typing import Optional
import datetime
import json
import re
# Create a logger object
logger = logging.getLogger(__name__)
# Set the logging level to DEBUG
logger.setLevel(logging.DEBUG)

class Item(BaseModel):
    stf: str

load_dotenv()

OPENAI_API_KEY = os.getenv("API2")

print(OPENAI_API_KEY)
client = OpenAI(api_key=OPENAI_API_KEY)
client.api_key = OPENAI_API_KEY
PROMPT = os.getenv("PROMPT")
PORT = int(os.getenv("PORT", 8000))

app = FastAPI()
#
# async def explain(question, answer):
#     if answer is None:
#         f= f"Expliquer la question suivante: {question}"
#     else:
#         f= f"Expliquer la question suivante: {question} et la reponse: {answer}"
#     try:
#         response = client.chat.completions.create(
#             model="gpt-4-0125-preview",
#             messages=[
#                 {
#                     "role": "system",
#                     "content": PROMPT
#                 },
#                 {
#                     "role": "user",
#                     "content": f
#                 },
#             ]
#         )
#         with open('response.txt', 'a+') as f:
#             # write datetime and response to file
#             time = datetime.datetime.now()
#             response_dict = response.model_dump()
#             response_json = json.dumps(response_dict)
#             f.write(str(time) + '\n')
#             f.write(response_json + '\n\n\n')
#
#         explanation = json.loads(response_json).get('choices')[0].get('message').get('content')
#
#         return explanation
#
#     except Exception as e:
#         print(f"An error occurred: {e}")
#         return None
#


async def generate_quiz_questions(course_content):
    print("Generating quiz questions..." , course_content)

    try:
        response = client.chat.completions.create(
            #model="gpt-4-0125-preview",
            model="gpt-4-0125-preview",
            messages=[
                {
                    "role": "system",
                    "content": PROMPT
                },
                {
                    "role": "user",
                    "content": "desiver les contenue de ce cour pour creer un quiz dans ce style \'#### Q1: la question que vous genererez?\n- [ ] reponse fausse\n- [X] reponse correct\n- [ ] cutre reponse fausse\n\n\' content: " + course_content
                },
            ]
        )

        print("response", response)

        
        # with open('response.txt', 'a+') as f:
        #     # write datetime and response to file
        #     time = datetime.datetime.now()
        #     response_dict = response.model_dump()
        #     response_json = json.dumps(response_dict)
        #     f.write(str(time) + '\n') 
        #     f.write(response_json + '\n\n\n')

        response_dict = response.model_dump()
        response_json = json.dumps(response_dict)


        # Parse the response to generate quiz questions
        course_content = response_json
        json_dict = json.loads(course_content)

        logger.debug(f"Response: {json_dict}")


        quiz_string = json_dict.get('choices')[0].get('message').get('content')
        quiz_list = quiz_string.split("\n\n")

        quiz = []

        for quiz_item in quiz_list:

            lines = quiz_item.split("\n")
            
            question = lines[0][5:]
            options = [re.sub(r"^- ", "", line) for line in lines[1:]]

            answer = next((option[4:] for option in options if "[X]" in option), None)
            options = [option[4:] for option in options]

            if (question.startswith("I'm sorry, but I cannot fulfill your request to provide the content of the course")):
                return []
            
            elif (question and len(options) > 0):
                quiz.append({"question": question, "options": options, "answer": answer})

        return quiz

    except Exception as e:
        print(f"An error occurred: {e}")
        return  []

@app.get("/generate")
async def get_questions(cours: Optional[str] = None):
    logger.debug(f"Received item: {cours}")
    try:
        return await generate_quiz_questions(cours)
    except Exception as e:
        logger.error(f"Error occurred: {e}")
        raise HTTPException(status_code=500, detail=str(e))
    
@app.get("/test")
async def get_questions(cours: Optional[str] = None):
    logger.debug(f"Received item: {cours}")
    try:
        return "test"
    except Exception as e:
        logger.error(f"Error occurred: {e}")
        raise HTTPException(status_code=500, detail=str(e))

# @app.get("/explain")
# async def explainQues(question: Optional[str] = None , answer: Optional[str] = None):
#     try:
#         return await explain(question, answer)
#     except Exception as e:
#         logger.error(f"Error occurred: {e}")
#         raise HTTPException(status_code=500, detail=str(e))
#
#
    

if __name__ == "__main__":
    uvicorn.run("main:app", host="localhost", port=PORT, reload=True)