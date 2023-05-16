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

    function displayStudents(students) {
        students = students.data;
        var ul = $('<ul>');
        $.each(students, function (key, student) {
            var li = $('<li>').text('ID ' + student.id + '. ' + student.name + ', ' +
                student.age + ' years, personal number ' + student.num + ', salary ' + student.salary);
            ul.append(li);
        });
        $('#studentsFound').empty().append(ul);
    }

    $('#getStudentBtn').click(function () {
        $('#dropdownListDiv').toggleClass('d-none');
        $('#buttonsSubmitAndClear').toggleClass('d-none');
    });

    $('#getDropdownList').change(function() {
        var selectedOption = $(this).find(':selected');
        var optionId = selectedOption.attr('id');
        var searchForms = document.querySelectorAll('.search-form');
        searchForms.forEach(function(form) {
            form.classList.add('d-none');
        });
        if (optionId === 'dropdownId') {
            $('#getStudentByIdForm').toggleClass('d-none');
            console.log("ID option selected");
        } else if (optionId === 'dropdownName') {
            $('#getStudentByNameForm').toggleClass('d-none');
            console.log("Name option selected");
        } else if (optionId === 'dropdownAge') {
            $('#getStudentByAgeForm').toggleClass('d-none');
            console.log("Age option selected");
        } else if (optionId === 'dropdownNum') {
            $('#getStudentByNumForm').toggleClass('d-none');
            console.log("Personal number option selected");
        } else if (optionId === 'dropdownSalary') {
            $('#getStudentBySalaryForm').toggleClass('d-none');
            console.log("Salary option selected");
        } else if (optionId === 'dropdownAll') {
            console.log("See the full list option selected");
            $.ajax({
                url: '/student',
                method: 'GET',
                success: displayStudents,
                error: function () {
                    alert('Error occurred while getting the whole list of students');
                }
            });
        }
    });

    $('#getStudentForm').submit(function (event) {
        event.preventDefault();
        const id = parseInt($('#getId').val());
        const name = $('#getName').val();
        const age = parseInt($('#getAge').val());
        const num = parseInt($('#getNum').val());
        const salary = parseFloat($('#getSalary').val());
        var searchParams = {};
        if (id){
            $.ajax({
                url: '/student/' + id,
                method: 'GET',
                success: displayStudents,
                error: function () {
                    alert('Error occurred while searching for a student by ID');
                }
            });
        } else if (name) {
            searchParams.name = name;
            $.ajax({
                url: '/student/search/name',
                method: 'GET',
                data: searchParams,
                success: displayStudents,
                error: function () {
                    alert('Error occurred while searching for students by name');
                }
            });
        } else if (age) {
            searchParams.age = age;
            $.ajax({
                url: '/student/search/age',
                method: 'GET',
                data: searchParams,
                success: displayStudents,
                error: function () {
                    alert('Error occurred while searching for students by age');
                }
            });
        } else if (num) {
            searchParams.num = num;
            $.ajax({
                url: '/student/search/num',
                method: 'GET',
                data: searchParams,
                success: displayStudents,
                error: function () {
                    alert('Error occurred while searching for students by personal number');
                }
            });
        } else if (salary) {
            searchParams.salary = salary;
            $.ajax({
                url: '/student/search/salary',
                method: 'GET',
                data: searchParams,
                success: displayStudents,
                error: function () {
                    alert('Error occurred while searching for students by salary');
                }
            });
        } else {
            alert('Invalid input. Fill either student ID or one of other parameters or leave all the fields empty')
        }
    });

    $('#clearGetSelection').click(function () {
        $('#studentsFound').empty();
    });

});