export class User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    type: string;
    birthDate: Date; // Ensure you handle date conversion appropriately
    field: Field; // Assuming 'Field' is another class you have defined
    
    
    constructor(
        id: number,
        firstName: string,
        lastName: string,
        email: string,
        password: string,
        type: string,
        birthDate: Date,
        field: Field
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.birthDate = birthDate;
        this.field = field;
    }
}

export class Field {}
