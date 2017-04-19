
$ajaxJsonGet = function(url,data,successHandler){
    if (typeof data === 'function'){
        successHandler = data;
        data={};
    }
    $.ajax({
        url:url,
        data:data,
        method:"GET",
        contentType:"json",
        success:function(data){
            successHandler(data)
        },
        error:function(jqXHR, textStatus, errorThrown){
            errorHandler(jqXHR, textStatus, errorThrown);
        }
    });
}
$ajaxJsonPost = function(url,data,successHandler){
    if (data === 'function'){
        successHandler = data;
        data={};
    }
    $.ajax({
        url:url,
        data:data,
        method:"POST",
        contentType:"json",
        success:function(data){
            successHandler(data)
        },
        error:function(jqXHR, textStatus, errorThrown){
            errorHandler(jqXHR, textStatus, errorThrown);
        }
    });
}

errorHandler = function(jqXHR, textStatus, errorThrown){
    console.log(testStatus);
}


