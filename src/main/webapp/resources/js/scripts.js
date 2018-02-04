var Book = {
    remove: function(id)
    {
        if (!confirm("Удалить книгу?")) return false;
        $.ajax({
            url: '/removebook',
            method: 'post',
            data: {id: id},
            dataType: 'json',
            success: function(data){
                if (data.success) location.reload();
                else alert("Ошибка при удалении!")
            },
            errors: function(){
                alert("Ошибка при удалении!");
            }
        });
    }
}