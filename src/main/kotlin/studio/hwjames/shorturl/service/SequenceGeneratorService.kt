package studio.hwjames.shorturl.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import studio.hwjames.shorturl.model.DBSequence

@Service
class SequenceGeneratorService {

    @Autowired
    private lateinit var mongoOperations: MongoOperations

    fun getSequenceNumber(sequenceName: String): Int {
        val query = Query(Criteria.where("id").`is`(sequenceName))
        val update = Update().inc("seq", 1)
        val counter = mongoOperations.findAndModify(
            query,
            update,
            options().returnNew(true).upsert(true),
            DBSequence::class.java
        )

        counter?.let {
            return counter.seq
        }

        return 1
    }
}