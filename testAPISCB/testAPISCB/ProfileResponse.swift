    
    // This file was generated from JSON Schema using quicktype, do not modify it directly.
    // To parse the JSON, add this file to your project and do:
    //
    //   let profileResponse = try? newJSONDecoder().decode(ProfileResponse.self, from: jsonData)
    
    import Foundation
    
    // MARK: - ProfileResponse
    struct ProfileResponse: Codable {
        let status: StatusProfile
        let data: DataClassProfile
    }
    
    // MARK: - DataClass
    struct DataClassProfile: Codable {
        let profile: Profile
    }
    
    // MARK: - Profile
    struct Profile: Codable {
        let partnerID, citizenID, passportNumber, alienID: String
        let thaiFirstName, thaiLastName, engFirstName, engLastName: String
        let birthDate, genderCode, mobile, email: String
        let address: Address
    }
    
    // MARK: - Address
    struct Address: Codable {
        let thaiAddressNumber, engAddressNumber, thaiAddressMoo, engAddressMoo: String
        let thaiAddressThanon, engAddressThanon, thaiAddressDistrict, engAddressDistrict: String
        let thaiAddressAmphur, engAddressAmphur, thaiAddressProvince, engAddressProvince: String
        let thaiAddressState, engAddressState, countryCode, zipCode: String
        let floorNumber, unitNumber, currentAddressFlag: String
    }
    
    // MARK: - Status
    struct StatusProfile: Codable {
        let code: Int
        let statusDescription: String
        
        enum CodingKeys: String, CodingKey {
            case code
            case statusDescription = "description"
        }
    }
