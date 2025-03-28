$(document).ready(function() {
    $("#searchForm").submit(function(event) {
        let errorMessages = [];

        let employeeId = $("#employeeId").val();
        let ageFrom = $("#ageFrom").val();
        let ageTo = $("#ageTo").val();
        let startDateFrom = $("#startDateFrom").val();
        let startDateTo = $("#startDateTo").val();
        let endDateFrom = $("#endDateFrom").val();
        let endDateTo = $("#endDateTo").val();

        // 数値チェック
        if (employeeId && isNaN(employeeId)) {
            errorMessages.push("社員IDは数値のみ許可されています。");
        }
        if (ageFrom && isNaN(ageFrom)) {
            errorMessages.push("年齢（開始）は数値のみ許可されています。");
        }
        if (ageTo && isNaN(ageTo)) {
            errorMessages.push("年齢（終了）は数値のみ許可されています。");
        }

        // 年齢の範囲チェック
        if (ageFrom && ageTo && parseInt(ageFrom) > parseInt(ageTo)) {
            errorMessages.push("開始年齢は終了年齢以下でなければなりません。");
        }

        // 日付チェック（yyyy/MM/dd形式）
        let dateRegex = /^\d{4}\/\d{2}\/\d{2}$/;

        function isValidDate(dateStr) {
            if (!dateRegex.test(dateStr)) return false; // 形式チェック
            let date = new Date(dateStr.replace(/\//g, "-"));
            return date instanceof Date && !isNaN(date.getTime()); // 存在する日付かチェック
        }

        function parseDate(dateStr) {
            return new Date(dateStr.replace(/\//g, "-"));
        }

        if (startDateFrom && !isValidDate(startDateFrom)) {
            errorMessages.push("開始日（開始）は yyyy/MM/dd の形式で正しい日付を入力してください。");
        }
        if (startDateTo && !isValidDate(startDateTo)) {
            errorMessages.push("開始日（終了）は yyyy/MM/dd の形式で正しい日付を入力してください。");
        }
        if (endDateFrom && !isValidDate(endDateFrom)) {
            errorMessages.push("終了日（開始）は yyyy/MM/dd の形式で正しい日付を入力してください。");
        }
        if (endDateTo && !isValidDate(endDateTo)) {
            errorMessages.push("終了日（終了）は yyyy/MM/dd の形式で正しい日付を入力してください。");
        }

        // 🔹 開始日（開始） > 開始日（終了）のチェック
        if (startDateFrom && startDateTo && isValidDate(startDateFrom) && isValidDate(startDateTo)) {
            if (parseDate(startDateFrom) > parseDate(startDateTo)) {
                errorMessages.push("開始日（開始）は開始日（終了）以前の日付にしてください。");
            }
        }

        // 🔹 終了日（開始） > 終了日（終了）のチェック
        if (endDateFrom && endDateTo && isValidDate(endDateFrom) && isValidDate(endDateTo)) {
            if (parseDate(endDateFrom) > parseDate(endDateTo)) {
                errorMessages.push("終了日（開始）は終了日（終了）以前の日付にしてください。");
            }
        }

        // エラーがある場合、アラート表示して送信をキャンセル
        if (errorMessages.length > 0) {
            alert(errorMessages.join("\n"));
            event.preventDefault();
        }
    });
});
