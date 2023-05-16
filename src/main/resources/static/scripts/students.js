$(document).ready(function () {

    /* Add student */

    $('#addStudentBtn').click(function () {
        $('#addStudentForm').toggleClass('d-none');
    });

    $("#addStudentForm").submit(function(event) {
        event.preventDefault();
        const name = $('#addName').val();
        const age = parseInt($('#addAge').val());
        const num = parseInt($('#addNum').val());
        const salary = parseFloat($('#addSalary').val());
        var student = {
            "name": name,
            "age": age,
            "num": num,
            "salary": salary
        };

        $.ajax({
            url: '/student',
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(student),
            success: function (student) {
                const addedStudent = student.data;
                console.log(`Student ${addedStudent.name} added successfully`);
                alert('Student added successfully');
                $('#addName').val('');
                $('#addAge').val('');
                $('#addNum').val('');
                $('#addSalary').val('');
            },
            error: function () {
                alert('Please check the values you gave as input');
            }
        });
    });

    /* Delete student by ID */

    $('#deleteStudentBtn').click(function () {
        $('#deleteStudentForm').toggleClass('d-none');
    });

    $("#deleteStudentForm").submit(function(event) {
        event.preventDefault();
        const id = $('#deleteId').val();
        $.ajax({
            url: '/student/' + id,
            type: 'DELETE',
            contentType: 'application/json',
            success: function (student) {
                const deletedStudent = student.data;
                console.log('Student deleted successfully');
                alert(`Student ${deletedStudent.name} deleted successfully`);
                $('#deleteId').val('');
            },
            error: function () {
                alert('Please check the student ID');
            }
        });
    });

    /* Update student */

    $('#updateStudentBtn').click(function () {
        $('#updateStudentForm').toggleClass('d-none');
    });

    $("#updateStudentForm").submit(function(event) {
        event.preventDefault();
        const id = parseInt($('#updateId').val());
        const name = $('#updateName').val();
        const age = parseInt($('#updateAge').val());
        const num = parseInt($('#updateNum').val());
        const salary = parseFloat($('#updateSalary').val());
        var student = {
            "id": id,
            "name": name,
            "age": age,
            "num": num,
            "salary": salary
        };

        $.ajax({
            url: '/student',
            type: 'PUT',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(student),
            success: function (student) {
                const updatedStudent = student.data;
                console.log('Student updated successfully');
                alert(`Student ${updatedStudent.name} updated successfully`);
                $('#updateId').val('');
                $('#updateName').val('');
                $('#updateAge').val('');
                $('#updateNum').val('');
                $('#updateSalary').val('');
            },
            error: function () {
                alert('Please check the values you gave as input');
            }
        });
    });

    /* Get students */

    $('#getStudentBtn').click(function () {
        $('#getStudentForm').toggleClass('d-none');
    });

    $('#getStudentForm').submit(function (event) {
        event.preventDefault();
        const id = parseInt($('#getId').val());
        const name = $('#getName').val();
        const age = parseInt($('#getAge').val());
        const num = parseInt($('#getNum').val());
        const salary = parseFloat($('#getSalary').val());
        if (!id && !name && !age && !num && !salary){
            $.ajax({
                url: '/student',
                method: 'GET',
                success: function (students) {
                    students = students.data;
                    var ul = $('<ul>');
                    $.each(students, function (key, student) {
                        var li = $('<li>').text('ID ' + student.id + '. ' + student.name + ', ' +
                            student.age + ' years, personal number ' + student.num + ', salary ' + student.salary);
                        ul.append(li);
                    });
                    $('#studentsFound').empty().append(ul);
                },
                error: function () {
                    alert('Error occurred while searching for students');
                }
            });
        }
        else if (id && !name && !age && !num && !salary){
            $.ajax({
                url: '/student/' + id,
                method: 'GET',
                success: function (student) {
                    student = student.data;
                    var ul = $('<ul>');
                    ul.append($('<li>').text('ID ' + student.id + '. ' + student.name + ', ' +
                        student.age + ' years, personal number ' + student.num + ', salary ' + student.salary));
                    $('#studentsFound').empty().append(ul);
                },
                error: function () {
                    alert('Error occurred while searching for students');
                }
            });
        }
        else if (!id && (name || age || num || salary)) {
            var searchParams = {};
            if (name) searchParams.name = name;
            if (age) searchParams.age = age;
            if (num) searchParams.num = num;
            if (salary) searchParams.salary = salary;
            $.ajax({
                url: '/student/search',
                method: 'GET',
                data: searchParams,
                success: function (students) {
                    students = students.data;
                    var ul = $('<ul>');
                    $.each(students, function (key, student) {
                        var li = $('<li>').text('ID ' + student.id + '. ' + student.name + ', ' +
                            student.age + ' years, personal number ' + student.num + ', salary ' + student.salary);
                        ul.append(li);
                    });
                    $('#studentsFound').empty().append(ul);
                },
                error: function () {
                    alert('Error occurred while searching for students');
                }
            });
        } else {
            alert('Invalid input. Fill either student ID or any other parameters or leave all the fields empty')
        }
    });

    $('#clearGetSelection').click(function () {
        $('#studentsFound').empty();
    });

});