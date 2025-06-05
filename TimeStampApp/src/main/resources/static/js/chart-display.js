let chart=null;
function displayChart(canvasId,labels,data){
    const canvas=document.getElementById(canvasId);
    if(!canvas)
        return;

    if(chart!=null)
        chart.destroy();

    const chartData = {
        labels: labels,
        datasets: [{
            data: data,
            backgroundColor: ['#90ee90', '#8fbc8f', '#228b22','#006400','#87cefa'],
            hoverOffset: 4
        }]
    };
    const config = {
        type: 'doughnut',
        data: chartData,
        options: {
            responsive: false,
            cutout: '45%',
            plugins: {
                tooltip: {
                    callbacks: {
                        label: function (context) {
                            const label = context.label || '';
                            const value = context.parsed || 0;
                            return `${label}: ${value} hour`;
                        }
                    }
                }
            }
        }
    };

    chart=new Chart(canvas, config);
}