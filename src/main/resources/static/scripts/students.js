$(document).ready(function () {
    $('#addStudentBtn').click(function () {
        $('#addStudentForm').toggleClass('d-none');
    });

    $("#addStudentForm").submit(function(event) {
        event.preventDefault();

        const name = $('#name').val();
        const age = parseInt($('#age').val());
        const num = parseInt($('#num').val());
        const salary = parseFloat($('#salary').val());

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
                $('#name').val('');
                $('#age').val('');
                $('#num').val('');
                $('#salary').val('');
            },
            error: function () {
                alert('Please check the values you gave as input');
            }
        });
    });
});