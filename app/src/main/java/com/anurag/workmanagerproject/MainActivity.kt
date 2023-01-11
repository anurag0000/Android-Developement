package com.anurag.workmanagerproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val workManager = WorkManager.getInstance(this)

    // workmanager work constraints
    lateinit var btnStartWork: Button
    lateinit var btnWorkStatus: Button
    lateinit var btnResetStatus: Button
    lateinit var btnWorkUIThread: Button

    // Work Retry
    lateinit var btnWorkerFail: Button
    lateinit var btnWorkerRetry: Button

    // Work Chains
    private lateinit var btnSingleChainSucceed: Button
    private lateinit var btnSingleChainFail: Button
    private lateinit var btnGroupChainSucceed: Button
    private lateinit var btnGroupChainFail: Button
    private lateinit var btnMultipleChainSucceed: Button
    private lateinit var btnMultipleChainFail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // workmanager work constraints
        btnStartWork = findViewById(R.id.btnStartWork)
        btnWorkStatus = findViewById(R.id.btnWorkStatus)
        btnResetStatus = findViewById(R.id.btnResetStatus)
        btnWorkUIThread = findViewById(R.id.btnWorkUIThread)

        // Work Retry
        btnWorkerFail = findViewById(R.id.btnWorkerFail)
        btnWorkerRetry = findViewById(R.id.btnWorkerRetry)

        // Work Chains
        btnSingleChainSucceed = findViewById(R.id.btnSingleChainSucceed)
        btnSingleChainFail = findViewById(R.id.btnSingleChainFail)
        btnGroupChainSucceed = findViewById(R.id.btnGroupChainSucceed)
        btnGroupChainFail = findViewById(R.id.btnGroupChainFail)
        btnMultipleChainSucceed = findViewById(R.id.btnMultipleChainSucceed)
        btnMultipleChainFail = findViewById(R.id.btnMultipleChainFail)


        // Work Chains <---------- starts ---------->

        btnSingleChainSucceed.setOnClickListener {
            val objectDetectionWorkRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(objectDetectionWorkRequest)
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)
                .enqueue()
        }

        btnSingleChainFail.setOnClickListener {
            val objectDetectionWorkRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to false))
                .build()
            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(objectDetectionWorkRequest)
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)
                .enqueue()
        }

        btnGroupChainSucceed.setOnClickListener {
            val objectDetectionWorkRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val objectDetectionWorkRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(listOf(objectDetectionWorkRequest1, objectDetectionWorkRequest2))
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)
                .enqueue()
        }

        btnGroupChainFail.setOnClickListener {
            val objectDetectionWorkRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val objectDetectionWorkRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to false))
                .build()
            val networkRequestWorkRequest = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val databaseWriteWorkRequest = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(listOf(objectDetectionWorkRequest1, objectDetectionWorkRequest2))
                .then(networkRequestWorkRequest)
                .then(databaseWriteWorkRequest)
                .enqueue()
        }

        btnMultipleChainSucceed.setOnClickListener {

            val objectDetectionWorkRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val objectDetectionWorkRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()
            val networkRequestWorkRequest1 = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val networkRequestWorkRequest2 = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()
            val databaseWriteWorkRequest1 = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val databaseWriteWorkRequest2 = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()

            val recommendation1 = workManager.beginWith(objectDetectionWorkRequest1)
                .then(networkRequestWorkRequest1)
                .then(databaseWriteWorkRequest1)

            val recommendation2 = workManager.beginWith(objectDetectionWorkRequest2)
                .then(networkRequestWorkRequest2)
                .then(databaseWriteWorkRequest2)

            val root = WorkContinuation.combine(listOf(recommendation1, recommendation2))

            root.enqueue()
        }

        btnMultipleChainFail.setOnClickListener {

            val objectDetectionWorkRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val objectDetectionWorkRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()
            val networkRequestWorkRequest1 = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val networkRequestWorkRequest2 = OneTimeWorkRequestBuilder<NetworkRequestWorker>()
                .setInputData(workDataOf("SUCCESS" to false, "NAME" to "TWO"))
                .build()
            val databaseWriteWorkRequest1 = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val databaseWriteWorkRequest2 = OneTimeWorkRequestBuilder<DatabaseWriteWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()

            val recommendation1 = workManager.beginWith(objectDetectionWorkRequest1)
                .then(networkRequestWorkRequest1)
                .then(databaseWriteWorkRequest1)

            val recommendation2 = workManager.beginWith(objectDetectionWorkRequest2)
                .then(networkRequestWorkRequest2)
                .then(databaseWriteWorkRequest2)

            val root = WorkContinuation.combine(listOf(recommendation1, recommendation2))

            root.enqueue()
        }

        // Work Chains <---------- ends ---------->


        // Work Retry <---------- starts ---------->
        btnWorkerFail.setOnClickListener{
            val workRequest = OneTimeWorkRequestBuilder<WorkerFail>()
                .build()

            workManager.enqueue(workRequest)
        }

        btnWorkerRetry.setOnClickListener {
            val workRequest = OneTimeWorkRequestBuilder<WorkerRetry>()
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    10,
                    TimeUnit.SECONDS
                )
                .build()

            workManager.enqueue(workRequest)
        }
        // Work Retry <---------- ends ---------->




        // workmanager work constraints <---------- starts ---------->

        btnStartWork.setOnClickListener {
            //val workRequest = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            /*val data = Data.Builder()
                .putString("WORK_MESSAGE", "Work Completed!")
                .build()*/

            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val data = workDataOf("WORK_MESSAGE" to  "Work Completed!")
            val workRequest = OneTimeWorkRequestBuilder<SimpleWorker>()
                .setInputData(data)
                .setConstraints(constraints)
                .build()

            val periodicWorkRequest = PeriodicWorkRequestBuilder<SimpleWorker>(
                5, TimeUnit.MINUTES,
            1, TimeUnit.MINUTES
            ).build()

            workManager.enqueue(workRequest)
        }

        btnWorkStatus.setOnClickListener {
            val toast = Toast.makeText(this, "The work status is: ${WorkStatusSingleton.workMessage}", Toast.LENGTH_SHORT)
            toast.show()
        }

        btnResetStatus.setOnClickListener {
            WorkStatusSingleton.workComplete = false
        }

        btnWorkUIThread.setOnClickListener {
            Thread.sleep(10000)
            WorkStatusSingleton.workComplete = true
        }

        // workmanager work constraints <---------- ends ---------->
    }
}



