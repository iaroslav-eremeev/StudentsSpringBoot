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
                alert(`Student ${addedStudent.name} added successfully`);
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
        const submitButton = $('#submitGetStudentForm');
        submitButton.removeClass('d-none');
        const copyToUpdateButton = $('#copyToUpdateButton');
        copyToUpdateButton.addClass('d-none');
        const label = $('#formInputLabel');
        const inputSelector = $('#formInput');
        inputSelector.attr('type', 'number');
        var selectedOption = $(this).find(':selected');
        var optionId = selectedOption.attr('id');
        if (optionId === 'dropdownId') {
            label.text("Student ID:");
            inputSelector.data('getParam', 'id');
            copyToUpdateButton.removeClass('d-none');
        } else if (optionId === 'dropdownName') {
            label.text("Name:");
            inputSelector.attr('type', 'text');
            inputSelector.data('getParam', 'name');
        } else if (optionId === 'dropdownAge') {
            label.text("Age:");
            inputSelector.data('getParam', 'age');
        } else if (optionId === 'dropdownNum') {
            label.text("Personal number:");
            inputSelector.data('getParam', 'num');
        } else if (optionId === 'dropdownSalary') {
            label.text("Salary:");
            inputSelector.data('getParam', 'salary');
        } else if (optionId === 'dropdownAll') {
            submitButton.addClass('d-none');
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

    $('#submitGetStudentForm').click(function (event) {
        event.preventDefault();
        const inputSelector = $('#formInput');
        const value = inputSelector.val();
        const parameter = inputSelector.data('getParam');
        if (parameter === 'id'){
            $.ajax({
                url: '/student/' + value,
                method: 'GET',
                success: function (student) {
                    student = student.data;
                    var ul = $('<ul>');
                    var li = $('<li>').text('ID ' + student.id + '. ' + student.name + ', ' +
                            student.age + ' years, personal number ' + student.num + ', salary ' + student.salary);
                    ul.append(li);
                    $('#studentsFound').empty().append(ul);
                    // Copy the code to update part so as not to do it manually
                    $('#copyToUpdateButton').click(function (event) {
                        $('#updateId').val('').val(student.id);
                        $('#updateName').val('').val(student.name);
                        $('#updateAge').val('').val(student.age);
                        $('#updateNum').val('').val(student.num);
                        $('#updateSalary').val('').val(student.salary);
                    });
                },
                error: function () {
                    alert('No student with such ID found');
                }
            });
        } else {
            $.ajax({
                url: '/student/search/' + parameter + "?" + parameter + "=" + value,
                method: 'GET',
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