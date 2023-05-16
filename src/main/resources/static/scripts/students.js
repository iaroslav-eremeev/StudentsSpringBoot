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
        $('#getDropdownList').selectedIndex = -1;
        $('#dropdownListDiv').toggleClass('d-none');
        $('#buttonsSubmitAndClear').toggleClass('d-none');
    });

    $('#getDropdownList').change(function() {
        const form = $('#getStudentForm');
        form.removeClass('d-none');
        const inputSelector = $('#formInput');
        inputSelector.removeClass("idInput");
        inputSelector.attr('type', 'number');
        var selectedOption = $(this).find(':selected');
        var optionId = selectedOption.attr('id');
        if (optionId === 'dropdownId') {
            $('#formInputLabel').text("Student ID:");
            inputSelector.addClass("idInput");
        } else if (optionId === 'dropdownName') {
            $('#formInputLabel').text("Name:");
            inputSelector.attr('type', 'text');
        } else if (optionId === 'dropdownAge') {
            $('#formInputLabel').text("Age:");
        } else if (optionId === 'dropdownNum') {
            $('#formInputLabel').text("Personal number:");
        } else if (optionId === 'dropdownSalary') {
            $('#formInputLabel').text("Salary:");
        } else if (optionId === 'dropdownAll') {
            form.addClass('d-none');
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
        const inputSelector = $('#formInput');
        const parameter = inputSelector.val();
        if (inputSelector.hasClass("idInput")){
            $.ajax({
                url: '/student/' + parameter,
                method: 'GET',
                success: displayStudents,
                error: function () {
                    alert('Error occurred while searching for a student by ID');
                }
            });
        } else {
            $.ajax({
                url: '/student/search/name',
                method: 'GET',
                data: parameter,
                success: displayStudents,
                error: function () {
                    alert('Error occurred while searching for students by name');
                }
            });
        }
    });

    $('#clearGetSelection').click(function () {
        $('#studentsFound').empty();
        $('#getDropdownList').selectedIndex = -1;
    });
});