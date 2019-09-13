package app.admin

import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type

class StatisticsTagLib {
    static defaultEncodeAs = [taglib:'raw']
    def statisticsService
    static namespace = 's'

    def top = { attrs, body ->
        def type = attrs.type
        def result = []
        def counter = 0
        switch (type) {
            case "tags":
                result = statisticsService.getTopTags()
                counter = Tag.count()
                break
            case "types":
                result = statisticsService.getTopTypes()
                counter = Type.count()
                break
            case "publishers":
                result = statisticsService.getTopPublishers()
                counter = Publisher.count()
                break
            default:
                result = []
                break
        }
        if(result) {
            out << "<strong>${type.capitalize()} (${counter})</strong> <ul>" + result.each { "<li>" + it + "</li>"} + "</ul>"
        } else {
            out << ""
        }
    }
}
