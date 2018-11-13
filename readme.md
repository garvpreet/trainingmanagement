APIs of Training Management:

1. GET all trainings:
URL: http://localhost:8082/trainings/training
RESPONSE: 
[
    {
        "id": 1,
        "title": "Java Spring",
        "description": "This is a dummy description for training for Java spring",
        "hours": 20.5
    }
]


2. GET training by ID:
URL: http://localhost:8082/trainings/training/{training_id}
RESPONSE:
{
    "id": 1,
    "title": "Java Spring",
    "description": "This is a dummy description for training for Java spring",
    "hours": 20.5
}


3. POST training
URL: http://localhost:8082/trainings/training
REQUEST:
{
    "title": "Java REST Services",
    "description": "This is a dummy description for training of Java REST Services",
    "hours": 50
}
RESPONSE:
{
    "success": true,
    "message": "Training saved successfully"
}


4. PUT training
URL: http://localhost:8082/trainings/training/{training_id}
REQUEST:
{
    "title": "Java REST Services New",
    "description": "This is a dummy description for training of Java REST Services!",
    "hours": 500
}
RESPONSE:
{
    "success": true,
    "message": "Training updated successfully"
}


5. DELETE training
URL: http://localhost:8082/trainings/training/{training_id}
RESPONSE:
{
    "success": true,
    "message": "Training deleted successfully"
}