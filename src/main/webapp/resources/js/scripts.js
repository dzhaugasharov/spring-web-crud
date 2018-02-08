var Book = {
    remove: function(id)
    {
        if (!confirm("Удалить книгу?")) return false;
        $.ajax({
            url: '/ajax?action=delete',
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
    },

    readAlready: function(id)
    {
        $.ajax({
            url: '/ajax?action=read_already',
            method: 'post',
            data: {id: id},
            dataType: 'json',
            success: function(data){
                if (data.success)
                {
                    var td = $("tr.book-"+id).find(".status-col"),
                        icn = $("tr.book-"+id).find('.status-btn-icn');
                    if (data.readAlready)
                    {
                        td.text('прочитана');
                        td.addClass('text-success');
                        icn.addClass('fa-eye-slash');
                        icn.removeClass('fa-eye');
                    }
                    else
                    {
                        td.text('не прочитана');
                        td.removeClass('text-success');
                        icn.removeClass('fa-eye-slash');
                        icn.addClass('fa-eye');
                    }
                }
                else alert("Ошибка при изменении статуса!")
            },
            errors: function(){
                alert("Ошибка при удалении!");
            }
        });
    }
}