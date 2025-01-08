document.getElementById('search-form').addEventListener('submit', function (e) {
    e.preventDefault(); // 防止表單提交刷新頁面
    const query = document.getElementById('query').value;
    const resultsDiv = document.getElementById('results');
    const resultsContainer = document.getElementById('results-container');
    resultsDiv.innerHTML = '<p>搜尋中...</p>';

    fetch(`/api/search?q=${encodeURIComponent(query)}`)
        .then(response => response.json())
        .then(data => {
            resultsDiv.innerHTML = '';
            resultsContainer.style.display = 'flex';
            if (Object.keys(data).length === 0) {
                resultsDiv.innerHTML = '<p>沒有找到結果。</p>';
                return;
            }
            for (const [title, url] of Object.entries(data)) {
                const itemDiv = document.createElement('div');
                itemDiv.classList.add('result-item');

                const link = document.createElement('a');
                link.href = url;
                link.target = '_blank';

                // 高亮顯示關鍵字
                const highlightedTitle = title.replace(new RegExp(query, 'gi'), match => `<span class="highlight">${match}</span>`);
                link.innerHTML = highlightedTitle;

                itemDiv.appendChild(link);
                resultsDiv.appendChild(itemDiv);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            resultsDiv.innerHTML = '<p>發生錯誤，請稍後再試。</p>';
        });

    // 隱藏首頁內容，顯示搜尋結果頁面
    document.querySelector('.container').style.display = 'none';
    document.getElementById('results-container').style.display = 'flex';
    document.getElementById('results-query').value = query; // 在搜尋結果頁面的搜尋欄顯示之前的搜尋關鍵字
});

document.getElementById('results-search-form').addEventListener('submit', function (e) {
    e.preventDefault(); // 防止表單提交刷新頁面
    const query = document.getElementById('results-query').value;
    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = '<p>搜尋中...</p>';

    fetch(`/api/search?q=${encodeURIComponent(query)}`)
        .then(response => response.json())
        .then(data => {
            resultsDiv.innerHTML = '';
            if (Object.keys(data).length === 0) {
                resultsDiv.innerHTML = '<p>沒有找到結果。</p>';
                return;
            }
            for (const [title, url] of Object.entries(data)) {
                const itemDiv = document.createElement('div');
                itemDiv.classList.add('result-item');

                const link = document.createElement('a');
                link.href = url;
                link.target = '_blank';

                // 高亮顯示關鍵字
                const highlightedTitle = title.replace(new RegExp(query, 'gi'), match => `<span class="highlight">${match}</span>`);
                link.innerHTML = highlightedTitle;

                itemDiv.appendChild(link);
                resultsDiv.appendChild(itemDiv);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            resultsDiv.innerHTML = '<p>發生錯誤，請稍後再試。</p>';
        });
});

document.getElementById('back-button').addEventListener('click', function () {
    // 隱藏搜尋結果頁面，顯示首頁內容
    document.getElementById('results-container').style.display = 'none';
    document.querySelector('.container').style.display = 'flex';
});
