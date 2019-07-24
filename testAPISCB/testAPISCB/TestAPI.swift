// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let testAPI = try? newJSONDecoder().decode(TestAPI.self, from: jsonData)

import Foundation

// MARK: - TestAPI
struct TestAPI: Codable {
    let status: Status
    let data: DataClass
}

// MARK: - DataClass
struct DataClass: Codable {
    let callbackURL: String
    
    enum CodingKeys: String, CodingKey {
        case callbackURL = "callbackUrl"
    }
}

// MARK: - Status
struct Status: Codable {
    let code: Int
    let statusDescription: String
    
    enum CodingKeys: String, CodingKey {
        case code
        case statusDescription = "description"
    }
}
