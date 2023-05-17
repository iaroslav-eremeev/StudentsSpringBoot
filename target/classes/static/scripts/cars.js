$(document).ready(function () {

    function populateStudentsDropdown() {
        $.ajax({
            url: '/student',
            method: 'GET',
            success: function(students) {
                students = students.data;
                var dropdownAdd = $('#addStudent');
                var dropdownUpdate = $('#updateStudent');
                var dropdownGet = $('#getStudent');
                dropdownAdd.empty();
                dropdownUpdate.empty();
                dropdownGet.empty();
                $.each(students, function(key, student) {
                    var optionText = 'ID ' + student.id + ' (' + student.name + ')';
                    dropdownAdd.append($('<option>').attr('data', student.id).text(optionText));
                    dropdownUpdate.append($('<option>').attr('data', student.id).text(optionText));
                    dropdownGet.append($('<option>').attr('data', student.id).text(optionText));
                });
            },
            error: function() {
                alert('Error occurred while fetching the list of students');
            }
        });
    }

    populateStudentsDropdown();

    /* Add car */

    $('#addCarBtn').click(function () {
        $('#addCarForm').toggleClass('d-none');
    });

    $("#addCarForm").submit(function(event) {
        event.preventDefault();
        const brand = $('#addBrand').val();
        const power = parseInt($('#addPower').val());
        const year = parseInt($('#addYear').val());
        const studentId = parseInt($('#addStudent option:selected').attr('data'));
        var car;
        $.ajax({
            url: '/student/' + studentId,
            method: 'GET',
            success: function (student) {
                car = {
                    "brand": brand,
                    "power": power,
                    "year": year,
                    "student": student.data
                };
                $.ajax({
                    url: '/car',
                    type: 'POST',
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(car),
                    success: function (car) {
                        const addedCar = car.data;
                        alert(`Car ${addedCar.brand} added successfully`);
                        $('#addBrand').val('');
                        $('#addPower').val('');
                        $('#addYear').val('');
                        $('#addStudent').val('');
                    },
                    error: function () {
                        alert('Please check the values you gave as input');
                    }
                });
            }
        });
    });

    /* Delete car by ID */

    $('#deleteCarBtn').click(function () {
        $('#deleteCarForm').toggleClass('d-none');
    });

    $("#deleteCarForm").submit(function(event) {
        event.preventDefault();
        const id = $('#deleteId').val();
        $.ajax({
            url: '/car/' + id,
            type: 'DELETE',
            contentType: 'application/json',
            success: function (car) {
                const deletedCar = car.data;
                alert(`Car ${deletedCar.brand} deleted successfully`);
                $('#deleteId').val('');
            },
            error: function () {
                alert('Please check the car ID');
            }
        });
    });

    /* Update car */

    $('#updateCarBtn').click(function () {
        $('#updateCarForm').toggleClass('d-none');
    });

    $("#updateCarForm").submit(function(event) {
        event.preventDefault();
        const id = parseInt($('#updateId').val());
        const brand = $('#updateBrand').val();
        const power = parseInt($('#updatePower').val());
        const year = parseInt($('#updateYear').val());
        const studentId = parseInt($('#updateStudent option:selected').attr('data'));
        $.ajax({
            url: '/student/' + studentId,
            method: 'GET',
            success: function (student) {
                var car = {
                    "id": id,
                    "brand": brand,
                    "power": power,
                    "year": year,
                    "student": student.data
                };
                $.ajax({
                    url: '/car',
                    type: 'PUT',
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(car),
                    success: function (car) {
                        const updatedCar = car.data;
                        alert(`Car ${updatedCar.brand} updated successfully`);
                        $('#updateId').val('');
                        $('#updateBrand').val('');
                        $('#updatePower').val('');
                        $('#updateYear').val('');
                        $('#updateStudent').val('');
                    },
                    error: function () {
                        alert('Please check the values you gave as input');
                    }
                });
            }
        });
    });

    /* Get cars */

    function displayCars(cars) {
        cars = cars.data;
        var ul = $('<ul>');
        $.each(cars, function (key, car) {
            var li = $('<li>').text('ID ' + car.id + '. ' + car.brand + ', ' +
                car.power + ' hp, year of production ' + car.year +
                ', owner ' + car.student.name + ' with ID ' + car.student.id);
            ul.append(li);
        });
        $('#carsFound').empty().append(ul);
    }

    $('#getCarBtn').click(function () {
        $('#getDropdownList').selectedIndex = -1;
        $('#dropdownListDiv').toggleClass('d-none');
        $('#buttonsSubmitAndClear').toggleClass('d-none');
        const form = $('#getCarForm');
        if (!form.hasClass('d-none')){
            form.toggleClass('d-none');
        }
    });

    $('#getDropdownList').change(function() {
        const form = $('#getCarForm');
        form.removeClass('d-none');
        const submitButton = $('#submitGetCarForm');
        submitButton.removeClass('d-none');
        const copyToUpdateButton = $('#copyToUpdateButton');
        copyToUpdateButton.addClass('d-none');
        const label = $('#formInputLabel');
        const inputSelector = $('#formInput');
        inputSelector.removeClass('d-none');
        inputSelector.attr('type', 'number');
        var selectedOption = $(this).find(':selected');
        var optionId = selectedOption.attr('id');
        $('#getStudent').addClass('d-none');
        if (optionId === 'dropdownId') {
            label.text("Car ID:");
            inputSelector.data('getParam', 'id');
            copyToUpdateButton.removeClass('d-none');
        } else if (optionId === 'dropdownBrand') {
            label.text("Brand:");
            inputSelector.attr('type', 'text');
            inputSelector.data('getParam', 'brand');
        } else if (optionId === 'dropdownPower') {
            label.text("Power:");
            inputSelector.data('getParam', 'power');
        } else if (optionId === 'dropdownYear') {
            label.text("Year of production:");
            inputSelector.data('getParam', 'year');
        } else if (optionId === 'dropdownStudent') {
            label.text("Student:");
            inputSelector.data('getParam', 'studentId');
            inputSelector.addClass('d-none');
            $('#getStudent').removeClass('d-none');
        } else if (optionId === 'dropdownAll') {
            submitButton.addClass('d-none');
            form.addClass('d-none');
            $.ajax({
                url: '/car',
                method: 'GET',
                success: displayCars,
                error: function () {
                    alert('Error occurred while getting the whole list of cars');
                }
            });
        } else {
            submitButton.addClass('d-none');
            form.addClass('d-none');
        }
    });

    $('#submitGetCarForm').click(function (event) {
        event.preventDefault();
        const inputSelector = $('#formInput');
        const value = inputSelector.val();
        const parameter = inputSelector.data('getParam');
        if (parameter === 'id'){
            $.ajax({
                url: '/car/' + value,
                method: 'GET',
                success: function (car) {
                    car = car.data;
                    var ul = $('<ul>');
                    var li = $('<li>').text('ID ' + car.id + '. ' + car.brand + ', ' +
                        car.power + ' hp, year of production ' + car.year +
                        ', owner ' + car.student.name + ' with ID ' + car.student.id);
                    ul.append(li);
                    $('#carsFound').empty().append(ul);
                    // Copy the code to update part so as not to do it manually
                    $('#copyToUpdateButton').click(function (event) {
                        $('#updateId').val('').val(car.id);
                        $('#updateBrand').val('').val(car.brand);
                        $('#updatePower').val('').val(car.power);
                        $('#updateYear').val('').val(car.year);
                        $('#updateStudent').val('').val(car.student);
                    });
                },
                error: function () {
                    alert('No car with such ID found');
                }
            });
        } else if (parameter === 'studentId'){
            const studentId = parseInt($('#getStudent option:selected').attr('data'));
            $.ajax({
                url: '/car/search/studentId?' + parameter + "=" + studentId,
                method: 'GET',
                success: displayCars,
                error: function () {
                    alert('Error occurred while searching for cars by brand');
                }
            });
        } else {
            $.ajax({
                url: '/car/search/' + parameter + "?" + parameter + "=" + value,
                method: 'GET',
                success: displayCars,
                error: function () {
                    alert('Error occurred while searching for cars by brand');
                }
            });
        }
        // Clear inputSelector
        inputSelector.val('');
    });

    $('#clearGetSelection').click(function () {
        $('#carsFound').empty();
        $('#getDropdownList').selectedIndex = -1;
        // Clear inputSelector
        $('#formInput').val('');
    });
});