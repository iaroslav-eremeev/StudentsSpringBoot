$(document).ready(function () {
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
            success: function (data) {
                console.log('Student added successfully');
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
            success: function (data) {
                const deletedStudent = data.data;
                console.log('Student deleted successfully');
                alert(`Student ${deletedStudent.name} deleted successfully`);
                $('#deleteId').val('');
            },
            error: function () {
                alert('Please check the student ID');
            }
        });
    });

});